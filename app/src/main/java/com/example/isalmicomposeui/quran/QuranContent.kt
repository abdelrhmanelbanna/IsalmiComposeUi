package com.example.isalmicomposeui.quran

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.isalmicomposeui.R
import com.example.isalmicomposeui.home.HomeActivity
import com.example.isalmicomposeui.quran.suraDetails.SuraDetailsActivity



    val suresList: List<String> = listOf(
        "الفاتحه","البقرة","آل عمران","النساء","المائدة","الأنعام","الأعراف","الأنفال","التوبة","يونس","هود"
        ,"يوسف","الرعد","إبراهيم","الحجر","النحل","الإسراء","الكهف","مريم","طه","الأنبياء","الحج","المؤمنون"
        ,"النّور","الفرقان","الشعراء","النّمل","القصص","العنكبوت","الرّوم","لقمان","السجدة","الأحزاب","سبأ"
        ,"فاطر","يس","الصافات","ص","الزمر","غافر","فصّلت","الشورى","الزخرف","الدّخان","الجاثية","الأحقاف"
        ,"محمد","الفتح","الحجرات","ق","الذاريات","الطور","النجم","القمر","الرحمن","الواقعة","الحديد","المجادلة"
        ,"الحشر","الممتحنة","الصف","الجمعة","المنافقون","التغابن","الطلاق","التحريم","الملك","القلم","الحاقة","المعارج"
        ,"نوح","الجن","المزّمّل","المدّثر","القيامة","الإنسان","المرسلات","النبأ","النازعات","عبس","التكوير","الإنفطار"
        ,"المطفّفين","الإنشقاق","البروج","الطارق","الأعلى","الغاشية","الفجر","البلد","الشمس","الليل","الضحى","الشرح"
        ,"التين","العلق","القدر","البينة","الزلزلة","العاديات","القارعة","التكاثر","العصر",
        "الهمزة","الفيل","قريش","الماعون","الكوثر","الكافرون","النصر","المسد","الإخلاص","الفلق","الناس"
    )




@Composable
fun QuranScreen() {
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (bg, icHeader,firstLine,sureNameText,secondLine,list) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = "Quran Background",
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(bg) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_quran_header),
            contentDescription = "Quran Header",

            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(icHeader) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Divider(
            color = Color(0xFFBFA36A),
            thickness = 2.dp,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(firstLine){
                    top.linkTo(icHeader.bottom)
                }
        )

        Text(
            text = "Sura Name",
            color = Color.Black,
            fontSize = 25.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .constrainAs(sureNameText){
                    top.linkTo(firstLine.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

        )
        Divider(
            color = Color(0xFFBFA36A),
            thickness = 2.dp,
            modifier = Modifier
                .constrainAs(secondLine){
                    top.linkTo(sureNameText.bottom)
                }
        )
        LazyColumn(
           contentPadding = PaddingValues(bottom =300.dp),
            modifier = Modifier
                .padding(vertical = 8.dp )
                .constrainAs(list){
                    top.linkTo(secondLine.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            items(suresList.size) { surah  ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = suresList[surah],
                        color = Color.Black,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                val intent = Intent(context, SuraDetailsActivity::class.java)
                                intent.putExtra("sura_name",suresList[surah])
                                intent.putExtra("sura_position", surah)
                                context.startActivity(intent)
                            },

                        textAlign = TextAlign.Center,

                        )

                }


        }

    }

}

}



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun QuranScreenPreview() {

    QuranScreen()
}