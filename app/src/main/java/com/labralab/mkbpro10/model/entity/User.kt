package com.labralab.mkbpro10.model.entity

class User(var uid: String) {

    constructor() : this("")

    lateinit var firstName: String
    lateinit var secondName: String
    lateinit var point: String
}