package com.freetreechair.sign_up.util

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.freetreechair.common.R
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.freetreechair.common.extension.getString
import com.freetreechair.sign_up.nickname.NicknameState
import com.freetreechair.sign_up.nickname.NicknameState.OVER_TEXT_LIMIT
import com.freetreechair.sign_up.nickname.NicknameState.NO_SPECIAL_CHARACTER
import com.freetreechair.sign_up.nickname.NicknameState.DUPLICATE_NICKNAME
import com.freetreechair.sign_up.nickname.NicknameState.ALLOWED_NICKNAME
import com.freetreechair.sign_up.nickname.NicknameState.HAS_NO_STATE

@BindingAdapter("updateBackground")
fun AppCompatEditText.updateBackground(nicknameState: NicknameState?) {
    nicknameState?.let {
        this.setBackgroundResource(
            when (it) {
                OVER_TEXT_LIMIT -> R.drawable.bg_button_red_stroke_15_dp
                NO_SPECIAL_CHARACTER -> R.drawable.bg_button_red_stroke_15_dp
                DUPLICATE_NICKNAME -> R.drawable.bg_button_red_stroke_15_dp
                ALLOWED_NICKNAME -> R.drawable.bg_button_orange_stroke_15_dp
                HAS_NO_STATE -> R.drawable.bg_button_black_stroke_15_dp
            }
        )
    }
}

@BindingAdapter("updateAppearance")
fun TextView.updateAppearance(nicknameState: NicknameState?) {
    nicknameState?.let {
        when (it) {
            OVER_TEXT_LIMIT -> {
                visibility = View.VISIBLE
                text = getString(R.string.over_limit_nickname)
            }
            NO_SPECIAL_CHARACTER -> {
                visibility = View.VISIBLE
                text = getString(R.string.invalid_nickname)
            }
            DUPLICATE_NICKNAME -> {
                visibility = View.VISIBLE
                text = getString(R.string.duplicated_nickname)
            }
            ALLOWED_NICKNAME -> {
                visibility = View.INVISIBLE
            }
            HAS_NO_STATE -> {
                visibility = View.VISIBLE
                text = null
            }
        }
    }
}

@BindingAdapter("updateBackground")
fun AppCompatButton.updateBackground(nicknameState: NicknameState?) {
    nicknameState?.let {
        when (it) {
            OVER_TEXT_LIMIT -> {
                isClickable = false
                setBackgroundResource(R.drawable.bg_button_gray_15_dp)
            }
            NO_SPECIAL_CHARACTER -> {
                isClickable = false
                setBackgroundResource(R.drawable.bg_button_gray_15_dp)
            }
            DUPLICATE_NICKNAME -> {
                isClickable = false
                setBackgroundResource(R.drawable.bg_button_gray_15_dp)
            }
            ALLOWED_NICKNAME -> {
                isClickable = true
                setBackgroundResource(R.drawable.bg_button_orange_15_dp)
            }
            HAS_NO_STATE -> {
                isClickable = false
                setBackgroundResource(R.drawable.bg_button_gray_15_dp)
            }
        }
    }
}

@BindingAdapter("updateAppearance")
fun AppCompatImageView.updateAppearance(nicknameState: NicknameState?) {
    nicknameState?.let {
        when (it) {
            OVER_TEXT_LIMIT -> {
                visibility = View.VISIBLE
                setBackgroundResource(R.drawable.ic_close)
            }
            NO_SPECIAL_CHARACTER -> {
                visibility = View.VISIBLE
                setBackgroundResource(R.drawable.ic_close)
            }
            DUPLICATE_NICKNAME -> {
                visibility = View.VISIBLE
                setBackgroundResource(R.drawable.ic_close)
            }
            ALLOWED_NICKNAME -> {
                visibility = View.VISIBLE
                setBackgroundResource(R.drawable.ic_check)
            }
            HAS_NO_STATE -> {
                visibility = View.INVISIBLE
            }
        }
    }
}

@BindingAdapter("setImageSourceToCircle")
fun AppCompatImageView.setImageSourceToCircle(imageUrl: String?) {
    imageUrl?.let { url ->
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(RequestOptions().circleCrop())
            .into(this)
    }
}

@BindingAdapter("setImageSourceToRoundRectangle")
fun AppCompatImageView.setImageSourceToRoundRectangle(imageUrl: String?) {
    imageUrl?.let { url ->
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions().centerCrop())
            .into(this)
    }
}
