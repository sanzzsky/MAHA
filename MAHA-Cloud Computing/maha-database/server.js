const express = require('express');
const bodyParser = require('body-parser');
const admin = require('firebase-admin');
const { authenticator } = require('otplib');
const bcrypt = require('bcrypt'); // For password hashing
const nodemailer = require('nodemailer'); // Untuk mengirim email OTP
require('dotenv').config(); // Untuk membaca variabel dari .env

const app = express();
const port = 3000;

app.use(bodyParser.json());

// Inisialisasi Firebase Admin SDK
const serviceAccount = require(process.env.FIREBASE_CREDENTIALS); // Path file diambil dari .env
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});

// Koneksi ke Firestore (Firebase)
const db = admin.firestore();

// Fungsi untuk menghasilkan OTP
const generateOTP = (secret) => {
    return authenticator.generate(secret); // Menghasilkan OTP berbasis waktu
};

// Fungsi untuk memverifikasi OTP
const verifyOTP = (otp, secret) => {
    return authenticator.verify({ token: otp, secret }); // Memverifikasi OTP
};

// Fungsi untuk mengirim OTP melalui email
const sendOTPEmail = (email, otp) => {
    const transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: process.env.EMAIL_USER, // Email diambil dari .env
            pass: process.env.EMAIL_PASS  // Password diambil dari .env
        }
    });

    const mailOptions = {
        from: process.env.EMAIL_USER,
        to: email,
        subject: 'OTP Verification',
        text: `Your OTP code is: ${otp}`
    };

    transporter.sendMail(mailOptions, (error, info) => {
        if (error) {
            console.log(error);
        } else {
            console.log('Email sent: ' + info.response);
        }
    });
};

// Endpoint untuk Sign Up
app.post("/signup", async (req, res) => {
    const { email, phoneNumber, password, confirmPassword, otp, personalData } = req.body;

    // Verifikasi password dan konfirmasi password
    if (password !== confirmPassword) {
        return res.status(400).send("Password dan Confirm Password tidak sama.");
    }

    // Mendapatkan secret untuk OTP
    const secret = authenticator.generateSecret();

    // Mengirim OTP ke email
    const generatedOTP = generateOTP(secret);
    sendOTPEmail(email, generatedOTP);

    // Verifikasi OTP
    const isValidOTP = verifyOTP(otp, secret); 
    if (!isValidOTP) {
        return res.status(400).send("OTP tidak valid.");
    }

    // Hash password sebelum menyimpannya
    const hashedPassword = await bcrypt.hash(password, 10);

    // Menyimpan data pengguna ke Firebase
    try {
        const userRef = db.collection("users").doc(email);
        await userRef.set({
            email: email,
            phoneNumber: phoneNumber,
            password: hashedPassword, // Simpan password yang sudah di-hash
            personalData: personalData,
            secret: secret // Simpan secret untuk OTP
        });
        return res.status(200).send("Pendaftaran berhasil.");
    } catch (error) {
        console.error("Error menyimpan data pengguna: ", error);
        return res.status(500).send("Terjadi kesalahan saat menyimpan data.");
    }
});

// Endpoint untuk Login
app.post("/login", async (req, res) => {
    const { email, password } = req.body;

    try {
        const userDoc = await db.collection("users").doc(email).get();

        if (!userDoc.exists) {
            return res.status(404).send("User tidak ditemukan.");
        }

        const user = userDoc.data();
        const isPasswordValid = await bcrypt.compare(password, user.password);

        if (!isPasswordValid) {
            return res.status(400).send("Password salah.");
        }

        return res.status(200).send("Login berhasil.");
    } catch (error) {
        console.error("Error login: ", error);
        return res.status(500).send("Terjadi kesalahan saat login.");
    }
});

// Menjalankan server di port 3000
app.listen(port, () => {
    console.log(`Server berjalan di http://localhost:${port}`);
});
