package com.example.common

interface Mapper<F, T> {
    fun map(from: F): T
}