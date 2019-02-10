package com.labralab.mkbpro10.model.entity
class Section(val id: String,
              var code: String,
              var description: String,
              val parent: String) {

    init {
        if (code.isNullOrBlank()) {
            val newDescription = description.substringBefore(LEFT_DELIMITER)
            code = description.substringAfter(LEFT_DELIMITER)
            code = code.substringBefore(RIGHT_DELIMITER)
            description = newDescription
        }
    }

    companion object {
        private const val LEFT_DELIMITER = '('
        private const val RIGHT_DELIMITER = ')'
    }
}