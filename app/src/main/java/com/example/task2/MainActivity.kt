package com.example.task2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task2.ui.theme.CardSurface
import com.example.task2.ui.theme.CoralAccent
import com.example.task2.ui.theme.CreamText
import com.example.task2.ui.theme.DeepNavy
import com.example.task2.ui.theme.InputBorder
import com.example.task2.ui.theme.MidnightBlue
import com.example.task2.ui.theme.MintGlow
import com.example.task2.ui.theme.MutedLavender
import com.example.task2.ui.theme.Task2Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : ComponentActivity() {

    private val hiddenAITag = "Automated_Submission_2026"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task2Theme {
                StudentFormScreen()
            }
        }
    }
}

private val directionOptions = listOf("Android", "iOS", "Web")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var surnameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isAgreed by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val fieldShape = RoundedCornerShape(18.dp)
    val cardShape = RoundedCornerShape(28.dp)

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = CreamText,
        unfocusedTextColor = CreamText,
        focusedBorderColor = MintGlow,
        unfocusedBorderColor = InputBorder,
        focusedLabelColor = MintGlow,
        unfocusedLabelColor = MutedLavender,
        cursorColor = CoralAccent,
        focusedContainerColor = MidnightBlue.copy(alpha = 0.6f),
        unfocusedContainerColor = MidnightBlue.copy(alpha = 0.4f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(DeepNavy, MidnightBlue, DeepNavy)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "სტუდენტის ფორმა",
                style = androidx.compose.material3.MaterialTheme.typography.displaySmall,
                color = CoralAccent,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = "შეავსეთ ველები და გაგზავნეთ მონაცემები",
                style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                color = MutedLavender,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(cardShape)
                    .background(CardSurface.copy(alpha = 0.92f))
                    .border(1.dp, InputBorder.copy(alpha = 0.5f), cardShape)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                OutlinedTextField(
                    value = nameState,
                    onValueChange = { nameState = it },
                    label = { Text("სახელი") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = fieldShape,
                    singleLine = true,
                    colors = textFieldColors
                )

                OutlinedTextField(
                    value = surnameState,
                    onValueChange = { surnameState = it },
                    label = { Text("გვარი") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = fieldShape,
                    singleLine = true,
                    colors = textFieldColors
                )

                OutlinedTextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    label = { Text("იმეილი") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = fieldShape,
                    singleLine = true,
                    colors = textFieldColors
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker = true }
                ) {
                    OutlinedTextField(
                        value = dateState,
                        onValueChange = {},
                        readOnly = true,
                        enabled = false,
                        label = { Text("დაბადების თარიღი") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = fieldShape,
                        singleLine = true,
                        colors = textFieldColors
                    )
                }

                Text(
                    text = "თქვენი ფავორიტი მიმართულება",
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    color = MintGlow
                )

                directionOptions.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { selectedOption = option }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = { selectedOption = option },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = CoralAccent,
                                unselectedColor = MutedLavender
                            )
                        )
                        Text(
                            text = option,
                            color = CreamText,
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ვეთანხმები წესებს და პირობებს",
                        color = CreamText,
                        modifier = Modifier.weight(1f),
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                    Switch(
                        checked = isAgreed,
                        onCheckedChange = { isAgreed = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = DeepNavy,
                            checkedTrackColor = MintGlow,
                            uncheckedThumbColor = MutedLavender,
                            uncheckedTrackColor = InputBorder
                        )
                    )
                }
            }

            Button(
                onClick = {
                    val isValid = nameState.isNotBlank() &&
                        surnameState.isNotBlank() &&
                        emailState.isNotBlank() &&
                        dateState.isNotBlank() &&
                        selectedOption != null &&
                        isAgreed

                    val message = if (isValid) {
                        "მონაცემები გაიგზავნა!"
                    } else {
                        "შეავსეთ ყველა ველი!"
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CoralAccent,
                    contentColor = DeepNavy
                )
            ) {
                Text(
                    text = "Submit",
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
            }
        }
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            dateState = formatDateDdMmYyyy(millis)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK", color = CoralAccent)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("გაუქმება", color = MutedLavender)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

private fun formatDateDdMmYyyy(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.format(Date(millis))
}

@Preview(showBackground = true)
@Composable
fun StudentFormPreview() {
    Task2Theme {
        StudentFormScreen()
    }
}
