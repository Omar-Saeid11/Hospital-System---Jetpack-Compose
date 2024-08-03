package com.example.hospitalsystem.data.models.cases.showCase


import com.google.gson.annotations.SerializedName

data class DataShowCase(
    @SerializedName("age")
    val age: String?,
    @SerializedName("analysis_id")
    val analysisId: String?,
    @SerializedName("blood_pressure")
    val bloodPressure: String?,
    @SerializedName("case_status")
    val caseStatus: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("doc_id")
    val docId: Int?,
    @SerializedName("doctor_id")
    val doctorId: String?,
    @SerializedName("fluid_balance")
    val fluidBalance: String?,
    @SerializedName("heart_rate")
    val heartRate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("measurement_note")
    val measurementNote: String?,
    @SerializedName("medical_record_note")
    val medicalRecordNote: String?,
    @SerializedName("nurse_id")
    val nurseId: String?,
    @SerializedName("patient_name")
    val patientName: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("respiratory_rate")
    val respiratoryRate: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("sugar_analysis")
    val sugarAnalysis: String?,
    @SerializedName("tempreture")
    val tempreture: String?
)