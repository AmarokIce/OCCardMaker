package club.someoneice.occard

data class OCData(
  // 基本
  val name: String       = "",
  val race: String       = "人类",
  val sex: Int           = 2,

  val stature: Int       = 2,
  val weight: Int        = 2,
  val health: Int        = 2,

  val month: String      = "1",
  val day: String        = "1",
  val age: String        = "1",
  val growth: Boolean    = true,

  // 个性与风格
  val myTitle: String    = "我",
  val action: Int        = 0,
  val talk: Int          = 0,
  val face: Int          = 0,
  val idea: Int          = 0,
  val individuality: Int = 0,

  // 其他
  val otherInfo: String  = ""
)
