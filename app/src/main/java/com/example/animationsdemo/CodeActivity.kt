package com.example.animationsdemo

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class CodeActivity : AppCompatActivity(), Animator.AnimatorListener {

    private var rotateAnimator: ObjectAnimator? = null
    private var translateAnimator: ObjectAnimator? = null
    private var scaleAnimator: ObjectAnimator? = null
    private var fadeAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)
    }

    fun rotateAnimation(view: View) {

        rotateAnimator = ObjectAnimator.ofFloat(targetImage, "rotation", 0.0f, -180.0f)
        rotateAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@CodeActivity)
            start()
        }
    }

    fun scaleAnimation(view: View) {

        scaleAnimator = ObjectAnimator.ofFloat(targetImage, "scaleX", 1.0f, 3.0f)
        scaleAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@CodeActivity)
            start()
        }
    }

    fun translateAnimation(view: View) {

        translateAnimator = ObjectAnimator.ofFloat(targetImage, "translationX", 0f, 200f)
        translateAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@CodeActivity)
            start()
        }

        // translateAnimator.cancel()
    }

    fun fadeAnimation(view: View) {

        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
        fadeAnimator?.apply {
            setTarget(targetImage)
            addListener(this@CodeActivity)
            start()
        }
    }

    // Implementation of AnimatorListener interface
    override fun onAnimationStart(animation: Animator?) {

        if (animation == scaleAnimator)
            Toast.makeText(this, "Scale Animation Started", Toast.LENGTH_SHORT).show()

        if (animation == rotateAnimator)
            Toast.makeText(this, "Rotate Animation Started", Toast.LENGTH_SHORT).show()

        if (animation == translateAnimator)
            Toast.makeText(this, "Translate Animation Started", Toast.LENGTH_SHORT).show()

        if (animation == fadeAnimator)
            Toast.makeText(this, "Fade Animation Started", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationRepeat(animation: Animator?) {

        Toast.makeText(this, "Animation Repeated", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationEnd(animation: Animator?) {

        Toast.makeText(this, "Animation Ended", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationCancel(animation: Animator?) {

        Toast.makeText(this, "Animation Cancelled", Toast.LENGTH_SHORT).show()
    }

    fun setFromCode(view: View) {

        // Root Animator Set
        val rootSet = AnimatorSet()

        // Flip Animation
        val flip = ObjectAnimator.ofFloat(targetImage, "rotationX", 0.0f, 360.0f)
        flip.duration = 500

        // Child Animator Set
        val childSet = AnimatorSet()

        // Scale Animations
        val scaleX = ObjectAnimator.ofFloat(targetImage, "scaleX", 1.0f, 1.5f)
        scaleX.duration = 500

        val scaleY = ObjectAnimator.ofFloat(targetImage, "scaleY", 1.0f, 1.5f)
        scaleY.duration = 500

//		rootSet.playSequentially(flip, childSet)
//		childSet.playTogether(scaleX, scaleY)

        rootSet.play(flip).before(childSet)
        childSet.play(scaleX).with(scaleY)

        rootSet.start()
    }

    fun viewPropertyAnimator(view: View) {
        val vpa = targetImage.animate()
        vpa.apply {
            duration = 1000
            rotationX(360.0f)
            scaleX(1.5f)
            scaleY(1.5f)
            translationX(200.0f)
            alpha(0.5f)
            interpolator = OvershootInterpolator()
            start()
        }
    }

    fun propertyValuesHolder(view: View) {


    }

}
