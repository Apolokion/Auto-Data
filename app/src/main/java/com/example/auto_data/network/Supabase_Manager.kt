package com.example.auto_data.network

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.auth.Auth

object SupabaseManager {
    private const val SUPABASE_URL = "https://dcuunluunshdbqpfisiv.supabase.co"

    // Anon Public Key - Available to everyone
    // No create,update,delete permissions. Only read
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRjdXVubHV1bnNoZGJxcGZpc2l2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTM0NTQ0NzIsImV4cCI6MjA2OTAzMDQ3Mn0.kmzcq7nORx_Cc1eb7npDHnjA9U9NlM3X7vSVtFXFy2Y"

    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Postgrest)
        install(Realtime)
        install(Auth)
    }
}