package com.example.isalmicomposeui.quran.suraDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.isalmicomposeui.R
import com.example.isalmicomposeui.quran.suresList


@Composable
fun SuraDetailsScreen(
    suraName: String, suraLines: List<String>, onBack: () -> Unit
    , modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = R.drawable.main_bg),
        contentDescription = "App Background",
        modifier = Modifier.fillMaxSize()
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.Transparent)
    ){


        Row(
            modifier = Modifier
                .padding( horizontal = 16.dp)
                .fillMaxWidth()
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow),
                contentDescription ="",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        onBack()
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    ,
                text = suraName,
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))

        }



            ConstraintLayout(
                modifier = Modifier
                    .padding(vertical = 50.dp)
                     // background inside the rounded container
                    .fillMaxWidth(0.85f)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(24.dp))
                    .fillMaxHeight(0.9f)
                    .background(Color.White),

            ) {
                val (title,arrow,line, content) = createRefs()

                Text(
                    text = "سورة $suraName",
                    fontSize =25.sp,
                    modifier = Modifier
                        .constrainAs(title){
                            top.linkTo(parent.top , margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },

                )

                Divider(
                    color = Color(0xFFBFA36A),
                    thickness = 1.dp,
                    modifier = Modifier
                        .constrainAs(line){
                            top.linkTo(title.bottom)
                        }
                        .padding(horizontal = 40.dp)
                )


                val annotated = buildAnnotatedString {
                    suraLines.forEachIndexed { index, line ->
                        // نص الآية
                        append(line)
                        append(" ")

                        // رقم الآية (ملون وصغير شوية)
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp,
                                color = Color(0xFFBFA36A), // نفس لون الخط الفاصل الذهبي
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("(${index + 1})")
                        }
                        append(" ") // مسافة بين الآيات
                    }
                }


                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(content) {
                            top.linkTo(line.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            height = androidx.constraintlayout.compose.Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(16.dp)
                ) {
                    item {
                        Text(
                            text = annotated,
                            fontSize = 22.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

//                LazyColumn(
//                    contentPadding = PaddingValues(bottom =300.dp),
//                    modifier = Modifier
//                        .padding(vertical = 8.dp )
//                        .constrainAs(content){
//                            top.linkTo(line.bottom)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        }
//                ) {
//                    items(suraLines.size) { content  ->
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(8.dp),
//                        ){
//                            Text(
//                                text = suraLines[content],
//                                color = Color.Black,
//                                fontSize = 22.sp,
//                                textAlign = TextAlign.Right,
//                                modifier = Modifier.padding(8.dp)
//                                    .fillMaxWidth(),
//                                )
//                        }
//
//                    }
//
//                }




            }


    }




    }




@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SureDetailsScreenPreview() {

    SuraDetailsScreen("الفاتحة", listOf(),{})

}