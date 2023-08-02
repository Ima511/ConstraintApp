package com.example.constraintapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.constraintapp.ui.theme.ConstraintAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintScreen()
                }
            }
        }
    }
}

@Composable
fun ConstraintScreen(){
        // 1st step :- Add dependency of constraint layout
        // 2nd step :- Creating the Constraint Layout
    
        ConstraintLayout {
            // 3rd step :- Create References for the
            // Composable to constraint
            val(redBox,
                blueBox,
                yellowBox,
                greenBox,
                blackBox,
                magentaBox,
                cyanBox) = createRefs()

         // 4th step :- Link the ConstraintLayout with
            // the composable reference objects

            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    /*
    * We can link:- start/end/top/bottom of the composable to
    * composables through the references object.
    *
    * You can also link to the parent Constraint layout with
    * parent object
    *
    * */
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                    height = Dimension.value(100.dp)

                })



            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Blue)
                .constrainAs(blueBox) {
                    top.linkTo(redBox.bottom)
                    end.linkTo(redBox.end)
                })

            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(blueBox.bottom)
                    end.linkTo(blueBox.end)
                })

            /*
            * Chains are widgets that are connected
            * to each other with bidirectional positional constraints.
            *
            * There are three constraining method when the chain constraint comes in used
            * 1) Spread, makes the widgets evenly distributed
            * 2) SpreadInside, makes the start and end widgets affixed to the boundaries at
            *       each end of the chain and the rest evenly distributed.
            * 3) Packed, makes the widgets stacked together (after margins are taken into account).
            * */

            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {
                    top.linkTo(yellowBox.bottom)
                }
            )

            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Black)
                .constrainAs(blackBox) {
                    top.linkTo(yellowBox.bottom)
                }
            )

            createHorizontalChain(greenBox, blackBox, chainStyle = ChainStyle.Packed)

            /*
            * Guidline is a class that contains helper objects in ConstraintLayout.
            * This helper object is not displayed/visible
            * on the device and is only used to help the layout.
            *
            *
            * There are two types of guidelines that can be implemented.
            * VerticalGuidelines, has zero width and height of the parent Constraintlayout
            * HorizontalGuidelines, has zero height and width of the Constraint Layout.
            * */

            val guideLine = createGuidelineFromBottom(0.3f)


            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    bottom.linkTo(guideLine)
                    end.linkTo(parent.end)
                })

            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Cyan)
                .constrainAs(cyanBox) {
                    bottom.linkTo(guideLine)

                })

            // Guid Lines


        }
}

@Preview
@Composable fun ConstraintScreenPreviw(){
    ConstraintScreen()
}

