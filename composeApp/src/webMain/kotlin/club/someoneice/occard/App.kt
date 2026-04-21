package club.someoneice.occard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import occardmaker.composeapp.generated.resources.Res
import occardmaker.composeapp.generated.resources.XiaolaiMono_Regular
import org.jetbrains.compose.resources.Font

val DATA = MutableStateFlow(OCData())

// TODO: 需要一张色卡。
@Composable
fun App() {
  MaterialTheme {
    Column(
      modifier = Modifier
        .background(Colors.YELLOW)
        .safeContentPadding()
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Spacer(Modifier.height(15.dp))
      Body()
    }
  }
}

@Composable
fun Body() {
  var showCard by remember { mutableStateOf(true) }

  AnimatedVisibility(
    visible = showCard
  ) {
    ShowEditor()
  }

  AnimatedVisibility(
    visible = !showCard
  ) {
    ShowPhoto()
  }

  Spacer(Modifier.height(10.dp))

  Button(
    onClick = { showCard = !showCard },
    colors = ButtonDefaults.buttonColors().copy(containerColor = Colors.MAUVE),
    modifier = Modifier.width(256.dp)
  ) {
    if (showCard) {
      FText("OK!")
    } else {
      FText("继续编辑!")
    }
  }
}


@Composable
fun ShowEditor() {
  Column {
    Card(
      modifier = Modifier.shadow(10.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.FLAMINGO)
    ) {
      Column(Modifier.padding(10.dp)) {
        FText("基本")
        Spacer(Modifier.height(20.dp))
        CreateBasicInfo()
      }
    }

    Spacer(Modifier.height(30.dp))

    Card(
      modifier = Modifier.shadow(10.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.FLAMINGO)
    ) {
      Column(Modifier.padding(10.dp)) {
        FText("个性")
        Spacer(Modifier.height(20.dp))
        CreatePersonality()
      }
    }
  }
}

@Composable
fun CreateBasicInfo() {
  var name by remember { mutableStateOf(DATA.value.name) }
  var race by remember { mutableStateOf(DATA.value.race) }
  var sex by remember { mutableIntStateOf(DATA.value.sex) }
  var stature by remember { mutableFloatStateOf(DATA.value.stature.toFloat()) }
  var weight by remember { mutableFloatStateOf(DATA.value.weight.toFloat()) }
  var health by remember { mutableFloatStateOf(DATA.value.health.toFloat()) }
  var month by remember { mutableStateOf(DATA.value.month) }
  var day by remember { mutableStateOf(DATA.value.day) }
  var age by remember { mutableStateOf(DATA.value.age) }
  var growth by remember { mutableStateOf(DATA.value.growth) }

  LaunchedEffect(name) {
    DATA.update {
      it.copy(name = name)
    }
  }

  LaunchedEffect(race) {
    DATA.update {
      it.copy(race = race)
    }
  }

  LaunchedEffect(sex) {
    DATA.update {
      it.copy(sex = sex)
    }
  }

  LaunchedEffect(stature) {
    DATA.update {
      it.copy(stature = stature.toInt())
    }
  }

  LaunchedEffect(weight) {
    DATA.update {
      it.copy(weight = weight.toInt())
    }
  }

  LaunchedEffect(health) {
    DATA.update {
      it.copy(health = health.toInt())
    }
  }

  LaunchedEffect(month) {
    DATA.update {
      it.copy(month = month)
    }
  }

  LaunchedEffect(day) {
    DATA.update {
      it.copy(day = day)
    }
  }

  LaunchedEffect(age) {
    DATA.update {
      it.copy(age = age)
    }
  }

  LaunchedEffect(growth) {
    DATA.update {
      it.copy(growth = growth)
    }
  }

  @Composable
  fun CreateName() {
    Column {
      FText(
        text = "名称/昵称:",
        Modifier.padding(bottom = 10.dp)
      )
      FTextField(name) {
        name = it
      }
    }
  }

  @Composable
  fun CreateRace() {
    Column {
      FText(
        text = "种族:",
        Modifier.padding(bottom = 10.dp)
      )
      FTextField(race) {
        race = it
      }
    }
  }

  @Composable
  fun CreateSex() {
    Column {
      FText(
        text = "性别:",
        Modifier.padding(bottom = 10.dp)
      )
      Row {
        FButton(sex == 0, "男") {
          sex = 0
        }
        Spacer(Modifier.width(5.dp))
        FButton(sex == 1, "女") {
          sex = 1
        }
        Spacer(Modifier.width(5.dp))
        FButton(sex == 2, "非二元") {
          sex = 2
        }
      }
    }
  }

  @Composable
  fun CreateStature() {
    FText("身高：${Enums.stature(stature.toInt())}")

    Slider(
      value = stature,
      onValueChange = { stature = it },
      valueRange = 0f..4f,
      steps = 3,
      modifier = Modifier.width(256.dp)
    )
  }

  @Composable
  fun CreateWeight() {
    FText("身材：${Enums.weight(weight.toInt())}")

    Slider(
      value = weight,
      onValueChange = { weight = it },
      valueRange = 0f..4f,
      steps = 3,
      modifier = Modifier.width(256.dp)
    )
  }

  @Composable
  fun CreateHealth() {
    FText("身体素质：${Enums.health(health.toInt())}")

    Slider(
      value = health,
      onValueChange = { health = it },
      valueRange = 0f..4f,
      steps = 3,
      modifier = Modifier.width(256.dp)
    )
  }

  Row {
    Card(
      modifier = Modifier.shadow(10.dp).size(300.dp, 300.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.ROSEWATER)
    ) {
      Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        // TODO: Input Bugs.
        CreateName()
        Spacer(Modifier.height(10.dp))
        CreateRace()
        Spacer(Modifier.height(10.dp))
        CreateSex()
      }
    }

    Spacer(Modifier.width(20.dp))

    Card(
      modifier = Modifier.shadow(10.dp).size(300.dp, 300.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.ROSEWATER)
    ) {
      Column(modifier = Modifier.padding(10.dp, 10.dp)) {
        CreateStature()
        Spacer(Modifier.height(15.dp))
        CreateWeight()
        Spacer(Modifier.height(15.dp))
        CreateHealth()
      }
    }
  }

  Spacer(Modifier.height(20.dp))

  Card(
    modifier = Modifier.shadow(10.dp).width(620.dp),
    colors = CardDefaults.cardColors().copy(containerColor = Colors.ROSEWATER)
  ) {
    Row(Modifier.padding(10.dp)) {
      FText("出生月:", Modifier.padding(top = 10.dp))
      FTextField(month, 48, month.toIntOrNull() == null) {
        month = it
      }

      Spacer(Modifier.width(10.dp))
      FText("出生日:", Modifier.padding(top = 10.dp))
      FTextField(day, 48, day.toIntOrNull() == null) {
        day = it
      }

      Spacer(Modifier.width(10.dp))
      FText("年龄:", Modifier.padding(top = 10.dp))
      FTextField(age, 48, age.toIntOrNull() == null) {
        age = it
      }

      Spacer(Modifier.width(10.dp))

      Checkbox(
        checked = growth,
        onCheckedChange = {
          growth = it
        }
      )
      FText("保持增龄", Modifier.padding(top = 10.dp))
    }
  }
}

@Composable
fun CreatePersonality() {
  var myTitle by remember { mutableStateOf(DATA.value.myTitle) }
  var action by remember { mutableIntStateOf(DATA.value.action) }
  var talk by remember { mutableIntStateOf(DATA.value.talk) }
  var face by remember { mutableIntStateOf(DATA.value.face) }
  var idea by remember { mutableIntStateOf(DATA.value.idea) }
  var individuality by remember { mutableStateOf(DATA.value.individuality) }

  LaunchedEffect(myTitle) {
    DATA.update {
      it.copy(myTitle = myTitle)
    }
  }

  LaunchedEffect(action) {
    DATA.update {
      it.copy(action = action)
    }
  }

  LaunchedEffect(talk) {
    DATA.update {
      it.copy(talk = talk)
    }
  }

  LaunchedEffect(face) {
    DATA.update {
      it.copy(face = face)
    }
  }

  LaunchedEffect(idea) {
    DATA.update {
      it.copy(idea = idea)
    }
  }

  LaunchedEffect(individuality) {
    DATA.update {
      it.copy(individuality = individuality)
    }
  }

  @Composable
  fun CreateButtons(text: String, left: String, right: String, index: Int, setValue: (Int) -> Unit) {
    Row {
      FText(text)
      Spacer(Modifier.width(180.dp))

      FText(left, Modifier.padding(bottom = 2.dp), 18)

      for (i in 0 .. 7) {
        Button(
          onClick = {
            setValue(i)
          },
          modifier = Modifier.size(20.dp),
          colors = ButtonDefaults.buttonColors()
            .copy(containerColor = if (index == i) Colors.BLUE else Colors.TEXT),
          shape = RoundedCornerShape(5.dp)
        ) {}

        if (i < 7) {
          Spacer(Modifier.width(5.dp))
        }
      }

      FText(right, Modifier.padding(bottom = 2.dp), 18)
    }
  }

  Column {
    Card(
      modifier = Modifier.shadow(10.dp).width(620.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.ROSEWATER)
    ) {
      Row(Modifier.padding(10.dp)) {
        FText("喜好的个人称呼：", Modifier.padding(top = 10.dp))
        Spacer(Modifier.width(120.dp))
        FTextField(myTitle) {
          myTitle = it
        }
      }
    }

    Spacer(Modifier.height(20.dp))

    Card(
      modifier = Modifier.shadow(10.dp).width(620.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.ROSEWATER)
    ) {
      Column(Modifier.padding(10.dp)) {
        CreateButtons("行为风格...", "从容", "利落", action) {
          action = it
        }

        CreateButtons("谈吐风格...", "温和", "严厉", talk) {
          talk = it
        }

        CreateButtons("表情风格...", "淡然", "丰富", face) {
          face = it
        }

        CreateButtons("逻辑风格...", "单纯", "慎重", idea) {
          idea = it
        }

        CreateButtons("综合个性...", "保守", "张扬", individuality) {
          individuality = it
        }
      }
    }
  }
}

@Composable
fun ShowPhoto() {
  FText("这个页面还没做完 :(")

  Column {
    Card(
      modifier = Modifier.shadow(10.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.FLAMINGO)
    ) {
      FText("你好！我是 ${DATA.value.name}!", Modifier.padding(10.dp), fontSize = 28)
    }

    Spacer(Modifier.height(15.dp))

    Card(
      modifier = Modifier.shadow(10.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.FLAMINGO)
    ) {
      Row(Modifier.padding(10.dp)) {
        Column {
          FText("性别 ${Enums.sex(DATA.value.sex)}, 是${DATA.value.race}")
          FText("生日：${DATA.value.month}/${DATA.value.day}")
          FText("已经 ${DATA.value.age} 岁, " + if (!DATA.value.growth) "年龄已经定格！" else "年龄自然成长！")
        }

        Column {
          FText("身高：${Enums.stature(DATA.value.stature)}")
          FText("身材：${Enums.weight(DATA.value.weight)}")
          FText("身体素质：${Enums.health(DATA.value.health)}")
        }
      }
    }

    Spacer(Modifier.height(15.dp))

    Card(
      modifier = Modifier.shadow(10.dp),
      colors = CardDefaults.cardColors().copy(containerColor = Colors.FLAMINGO)
    ) {
      Column(Modifier.padding(10.dp)) {
        FText("Generator")

      }
    }
  }
}

@Composable
fun FText(text: String, modifier: Modifier = Modifier, fontSize: Int = 24) {
  val font by mutableStateOf(FontFamily(Font(resource = Res.font.XiaolaiMono_Regular)))

  Text(
    text = text,
    fontFamily = font,
    modifier = modifier,
    fontSize = fontSize.sp
  )
}

@Composable
fun FTextField(value: String, width: Int = 256, error: Boolean = false, onValueChange: (String) -> Unit) {
  val font by mutableStateOf(FontFamily(Font(resource = Res.font.XiaolaiMono_Regular)))
  val style = TextStyle(
    fontFamily = font,
    fontSize = 24.sp
  )

  BasicTextField(
    value = value,
    onValueChange = onValueChange,
    textStyle = style,
    modifier = Modifier
      .background(Color.Transparent)
      .width(width.dp)
      .border(1.dp, (if (error) Color.Red else Color.Gray), RoundedCornerShape(4.dp))
      .padding(4.dp, 8.dp),
    singleLine = true
  )
}

@Composable
fun FButton(light: Boolean, text: String, onClick: () -> Unit) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors().copy(containerColor = run {
      if (light) {
        Colors.BLUE
      } else {
        Colors.TEXT
      }
    })
  ) {
    FText(text, fontSize = 18)
  }
}
