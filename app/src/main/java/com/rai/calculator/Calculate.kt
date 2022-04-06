package com.rai.calculator



fun calculate(string: String): String{
    val lexemes  = lexAnaliz(string)
    val lexemeBuffer = LexemeBuf(lexemes)
    return expr(lexemeBuffer).toString()
}

enum class LexemeType {
    LEFT_BRACKET, RIGHT_BRACKET,
    OP_PLUS, OP_MINUS, OP_MUL, OP_DIV,
    NUMBER,
    EOF;
}

data class Lexeme(val typeLexeme: LexemeType,val valueLexeme:String)

class LexemeBuf(private val lexemes: List<Lexeme>) {
    var pos = 0
        private set

    fun next(): Lexeme {
        return lexemes[pos++]
    }

    fun back() {
        pos--
    }

}


private fun lexAnaliz(string: String):List<Lexeme>{
    val lexemes = mutableListOf<Lexeme>()
    var pos = 0
    while (pos< string.length) {
        var c = string[pos]
        when(c){
            '(' ->  {lexemes.add(Lexeme(LexemeType.LEFT_BRACKET,c.toString()))
                pos++}
            ')' ->  {lexemes.add(Lexeme(LexemeType.RIGHT_BRACKET,c.toString()))
                pos++}
            '+' ->  {lexemes.add(Lexeme(LexemeType.OP_PLUS,c.toString()))
                pos++}
            '-' ->  {lexemes.add(Lexeme(LexemeType.OP_MINUS,c.toString()))
                pos++}
            '*' ->  {lexemes.add(Lexeme(LexemeType.OP_MUL,c.toString()))
                pos++}
            '/' ->  {lexemes.add(Lexeme(LexemeType.OP_DIV,c.toString()))
                pos++}
            else -> {
                if (c.isDigit()) {
                    var sb = ""
                    do {
                        sb += c
                        pos++
                        if (pos >= string.length) {
                            break
                        }
                        c = string[pos]
                    }while (c.isDigit())
                    lexemes.add(Lexeme(LexemeType.NUMBER,sb))
                }else{
                    if (c != ' '){
                        throw Exception("Неизвестный символ: $c")
                    }
                    pos++
                }
            }
        }

    }
    lexemes.add(Lexeme(LexemeType.EOF,""))
    return lexemes
}

private fun expr(lexemes: LexemeBuf): Int {
    val lexeme = lexemes.next()
    return if (lexeme.typeLexeme == LexemeType.EOF) {
        0
    } else {
        lexemes.back()
        plusMinus(lexemes)
    }
}

private fun plusMinus(lexemes: LexemeBuf): Int {
    var value = multDiv(lexemes)
    while (true) {
        val lexeme = lexemes.next()
        when (lexeme.typeLexeme) {
            LexemeType.OP_PLUS -> value += multDiv(lexemes)
            LexemeType.OP_MINUS -> value -= multDiv(lexemes)
            LexemeType.EOF, LexemeType.RIGHT_BRACKET -> {
                lexemes.back()
                return value
            }
            else -> throw Exception(
                "Неверное значение: " + lexeme.valueLexeme
                        + " в позиции: " + lexemes.pos
            //надо будет в ресурс стрингов запихнуть
            )
        }
    }
}

private fun multDiv(lexemes: LexemeBuf): Int {
    var value = factor(lexemes)
    while (true) {
        val lexeme = lexemes.next()
        when (lexeme.typeLexeme) {
            LexemeType.OP_MUL -> value *= factor(lexemes)
            LexemeType.OP_DIV -> value /= factor(lexemes)
            LexemeType.EOF, LexemeType.RIGHT_BRACKET, LexemeType.OP_PLUS, LexemeType.OP_MINUS -> {
                lexemes.back()
                return value
            }
            else -> throw Exception(
                "Неверное значение: " + lexeme.valueLexeme
                        + " в позиции: " + lexemes.pos
            )
        }
    }
}

private fun factor(lexemes: LexemeBuf): Int {
    var lexeme = lexemes.next()
    when (lexeme.typeLexeme) {
        LexemeType.NUMBER -> return lexeme.valueLexeme.toInt()
        LexemeType.LEFT_BRACKET -> {
            val value: Int = plusMinus(lexemes)
            lexeme = lexemes.next()
            if (lexeme.typeLexeme != LexemeType.RIGHT_BRACKET) {
                throw Exception(
                    "Неверное значение: " + lexeme.valueLexeme
                            + " в позиции: " + lexemes.pos
               )
            }
            return value
        }
        else -> throw Exception(
            "Неверное значение: " + lexeme.valueLexeme
                    + " в позиции: " + lexemes.pos
        )
    }
}