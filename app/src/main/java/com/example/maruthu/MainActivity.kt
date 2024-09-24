package com.example.maruthu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpAdapter()
    }

    private fun setUpAdapter() {
        val accounts = arrayListOf(
            AccountDetails(
                index = 0,
                icon = R.drawable.ic_qpay_wallet,
                bankCode = "QPay Balance",
                accountBalance = "₹2,36,000.47",
            ),
            AccountDetails(
                index = 1,
                icon = R.drawable.ic_sbi,
                bankCode = "SBI",
                bankName = "State Bank of India",
                accountBalance = "₹2,36,000.47",
            ),
            AccountDetails(
                index = 2,
                icon = R.drawable.ic_bob,
                bankCode = "BOB",
                bankName = "Bank of Baroda",
                accountBalance = "₹2,13,000.47",
            ),
            AccountDetails(
                index = 3,
                icon = R.drawable.ic_bank,
                bankCode = "Bank Name",
                accountBalance = "₹0.00",
            ),
        )
        val recyclerView = findViewById<RecyclerView>(R.id.uiRvBank)
        recyclerView.adapter = AccountAdapter(
            context = this,
            accounts = accounts,
            onAccountClicked = {
                onAccountClicked(accounts, it)
            }
        )
    }

    private fun onAccountClicked(
        accountsList: ArrayList<AccountDetails>,
        acc: AccountDetails
    ) {
        val uiTvBankText = findViewById<TextView>(R.id.uiTvBankText)
        val uiGrpBankDetails = findViewById<Group>(R.id.uiGrpBankDetails)
        val uiGrpWallet = findViewById<Group>(R.id.uiGrpWallet)
        val uiGrpNewAccount = findViewById<Group>(R.id.uiGrpNewAccount)
        val uiBtnStatement = findViewById<MaterialButton>(R.id.uiBtnStatement)
        val uiBtnTransfer = findViewById<MaterialButton>(R.id.uiBtnTransfer)
        when(acc.index) {
            accountsList.first().index -> {
                uiGrpNewAccount.visibility = View.GONE
                uiGrpBankDetails.visibility = View.GONE
                uiGrpWallet.visibility = View.VISIBLE
                uiBtnStatement.visibility = View.VISIBLE
                uiBtnStatement.text = "Add Money"
                uiBtnStatement.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_money)
                uiBtnStatement.iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START
                uiBtnTransfer.visibility = View.VISIBLE
            }
            accountsList.last().index -> {
                uiGrpNewAccount.visibility = View.VISIBLE
                uiGrpBankDetails.visibility = View.GONE
                uiGrpWallet.visibility = View.GONE
                uiBtnStatement.visibility = View.GONE
                uiBtnTransfer.visibility = View.GONE
            }
            else -> {
                uiGrpNewAccount.visibility = View.GONE
                uiGrpBankDetails.visibility = View.VISIBLE
                uiGrpWallet.visibility = View.GONE
                uiTvBankText.text = acc.bankName
                uiBtnStatement.visibility = View.VISIBLE
                uiBtnStatement.text = "Statement"
                uiBtnTransfer.visibility = View.VISIBLE
            }
        }
    }
}