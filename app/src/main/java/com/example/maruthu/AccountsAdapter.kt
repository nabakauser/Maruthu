package com.example.maruthu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AccountAdapter(
    private val context: Context,
    private val accounts: ArrayList<AccountDetails>,
    private val onAccountClicked: (AccountDetails) -> Unit,
) : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_bank, parent, false)
        return AccountViewHolder(view)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val  accountDetails = accounts[position]
        Glide.with(context)
            .load(accountDetails.icon)
            .override(40)
            .centerCrop()
            .into(holder.uiTvAccIcon)
        holder.uiTvAccName.text = accountDetails.bankCode
        holder.uiTvAccBalance.text = accountDetails.accountBalance
    }

    inner class AccountViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var uiTvAccIcon: ImageView = view.findViewById(R.id.uiIvWallet)
        var uiTvAccName: AppCompatTextView = view.findViewById(R.id.uiTvQpayWallet)
        var uiTvAccBalance: AppCompatTextView = view.findViewById(R.id.uiTvWalletBalance)
        private var uiCvAccount: CardView = view.findViewById(R.id.uiCvAccount)
        init {
            uiCvAccount.setOnClickListener {
                onAccountClicked(accounts[adapterPosition])
            }
        }
    }
}