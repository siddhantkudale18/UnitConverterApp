package com.example.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.systemBars.asPaddingValues()),
                    color = MaterialTheme.colorScheme.background
                ){
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    var conversionFactor = remember { mutableStateOf(1.0) }
    var oConversionFactor = remember { mutableStateOf(1.0) }

    fun convertUnits(){
        // ?: --> Elvis Operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { Text("Enter Value", color = Color.Black)},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                //textColor = Color.Black,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Green,
                unfocusedLabelColor = Color.Gray

            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(){
            Box(){
                // Input Button
                Button(
                    onClick = { iExpanded = true},
                    modifier = Modifier.width(130.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(4.dp)
                ){
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeter"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meter"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeter"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box(){
                // Output Button
                Button(
                    onClick = { oExpanded = true},
                    modifier = Modifier.width(130.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(4.dp)
                ){
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeter"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meter"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milimeter"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Result
        Text(
            "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}