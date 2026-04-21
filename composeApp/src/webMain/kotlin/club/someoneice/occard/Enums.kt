package club.someoneice.occard

object Enums {
  fun stature(index: Int): String {
    return when(index) {
      0 -> "侏儒"
      1 -> "偏矮"
      2 -> "常规"
      3 -> "较高"
      4 -> "魁梧"
      else -> "???"
    }
  }

  fun weight(index: Int): String {
    return when(index) {
      0 -> "贫血"
      1 -> "偏瘦"
      2 -> "匀称"
      3 -> "超重"
      4 -> "肥胖"
      else -> "???"
    }
  }

  fun sex(index: Int): String {
    return when(index) {
      0 -> "男"
      1 -> "女"
      2 -> "非二元"
      else -> "非二元"
    }
  }

  fun health(index: Int): String {
    return when(index) {
      0 -> "险死还生"
      1 -> "弱不禁风"
      2 -> "相对健康"
      3 -> "非常健康"
      4 -> "红光满面"
      else -> "???"
    }
  }
}