package org.venturus.br.composestates.data

import androidx.annotation.DrawableRes

data class Employee(
    val id: Int,
    val name: String,
    @DrawableRes val avatar: Int,
    val currentRole: String,
    val salary: Int,
    val currentProject: String,
    val competences: List<String>
)