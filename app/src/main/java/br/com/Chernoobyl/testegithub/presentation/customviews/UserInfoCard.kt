package br.com.chernoobyl.testegithub.presentation.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.chernoobyl.testegithub.R
import br.com.chernoobyl.testegithub.databinding.LayoutUserInfoCardBinding
import br.com.chernoobyl.testegithub.domain.entities.User
import br.com.chernoobyl.testegithub.presentation.shared.loadFromUrl

class UserInfoCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: LayoutUserInfoCardBinding =
        LayoutUserInfoCardBinding.inflate(LayoutInflater.from(context), this, true)

    private var mButtonClickListener: ((url: String?) -> Unit)? = null

    fun fillWithUser(user: User) {
        user.run {
            binding.imageView.loadFromUrl(avatarUrl)
            binding.loginTextView.text = context.getString(R.string.user_login_placeholder, login)
            binding.nameTextView.visibility = View.GONE
            binding.locationTextView.visibility = View.GONE
            binding.reposTextView.visibility = View.GONE
            binding.gotoRepoOutlinedButton.visibility = View.GONE
        }
    }

    fun fillWithFullUser(user: User) {
        user.run {
            binding.imageView.loadFromUrl(avatarUrl)
            binding.loginTextView.text = context.getString(R.string.user_login_placeholder, login)
            binding.nameTextView.text = name
            binding.locationTextView.text = location
            binding.reposTextView.text = context.getString(R.string.user_repos_placeholder, repos)
            binding.gotoRepoOutlinedButton.setOnClickListener {
                mButtonClickListener?.invoke(url)
            }
        }
    }

    override fun setOnClickListener(mListener: OnClickListener?) {
        binding.cardView.setOnClickListener(mListener)
    }

    fun setButtonClickListener(mListener: ((url: String?) -> Unit)?) {
        this.mButtonClickListener = mListener
    }
}