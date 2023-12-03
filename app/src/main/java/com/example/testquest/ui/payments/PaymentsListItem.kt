package com.example.testquest.ui.payments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testquest.R
import com.example.testquest.data.network.models.PaymentsResponse

@Composable
fun PaymentsListItem(
    payment: PaymentsResponse.Response,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Row {
            Text(
                text = stringResource(id = R.string.payments_id_label),
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = payment.id.toString(),
                fontSize = 10.sp
            )
        }
        Row {
            Text(
                text = stringResource(id = R.string.payments_title_label),
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${payment.title} ",
                fontSize = 10.sp
            )
        }
        if (payment.amount != null) {
            Row {
                Text(
                    text = stringResource(id = R.string.payments_amount_label),
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${payment.amount} ",
                    fontSize = 10.sp
                )
            }
        }
        if (payment.created != null) {
            Row {
                Text(
                    text = stringResource(id = R.string.payments_created_label),
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${payment.created} ",
                    fontSize = 10.sp
                )
            }
        }
    }
}