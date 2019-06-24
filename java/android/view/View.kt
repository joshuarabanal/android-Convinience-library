/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package android.view

import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.event.ActionEvent
import javax.swing.JButton
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.util.Log

/**
 *
 * @author Joshua
 */
open class View(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : Component() {
	//java swing objects
	var parentContainer: Component? = null
	var defaultWidth = 0
	var defaultHeight = 0
	var defaultPadding = 0
	var defaultLayoutMargin = 0
	var minimumWidth = 0
	var minimumHeight = 0
	var measuredWidth: Int = 0
	var measuredHeight: Int = 0
	private var paddingTop = 0
	private var paddingBottom = 0
	private var paddingLeft = 0
	private var paddingRight = 0
	private var marginTop = 0
	private var marginBottom = 0
	private var marginLeft = 0
	private var marginRight = 0
	/**
	 * positioning of this view relative to parents
	 */
	protected var top: Int = 0
	protected var left: Int = 0
	var width_ours: Int = 0
	var height_ours: Int = 0

	init {
		if (attrs != null) {
			val set = intArrayOf(
				android.R.attr.minWidth, // idx 0
				android.R.attr.minHeight, // idx 1,
				android.R.attr.padding,
				android.R.attr.paddingTop,
				android.R.attr.paddingBottom,
				android.R.attr.paddingLeft,
				android.R.attr.paddingRight,
				android.R.attr.layout_margin,
				android.R.attr.layout_marginTop,
				android.R.attr.layout_marginBottom,
				android.R.attr.layout_marginLeft,
				android.R.attr.layout_marginRight
			)
			val a = context!!.obtainStyledAttributes(
				attrs,
				set
			)
			minimumWidth = a!!.getDimensionPixelSize(0, defaultWidth)
			minimumHeight = a!!.getDimensionPixelSize(1, defaultHeight)
			val padding = a!!.getDimensionPixelSize(2, defaultPadding)
			paddingTop = a!!.getDimensionPixelSize(3, 0)
			paddingBottom = a!!.getDimensionPixelSize(4, 0)
			paddingLeft = a!!.getDimensionPixelSize(5, 0)
			paddingRight = a!!.getDimensionPixelSize(6, 0)
			val margin = a!!.getDimensionPixelSize(7, defaultLayoutMargin)
			marginTop = a!!.getDimensionPixelSize(8, margin)
			marginBottom = a!!.getDimensionPixelSize(9, margin)
			marginLeft = a!!.getDimensionPixelSize(10, margin)
			marginRight = a!!.getDimensionPixelSize(11, margin)
			a!!.recycle()
		}
	}

	fun layout(left: Int, top: Int, right: Int, bottom: Int) {
		this.top = top
		this.left = left
		this.setSize(Dimension(right - left, bottom - top))
		this.setLocation(top, left)
		this.setBounds(Rectangle(left, top, width, height))
	}

	fun requestLayout() {
		if(getParent() != null){
			Log.i("request layout called", "request layout called");
			getParent().validate()
		}
		else{
			Log.i("request layout called", "unable due to null parent");
		}
	}

	protected fun onMeasure(
		widthMeasureSpec: Int,
		heightMeasureSpec: Int
	) {
		var widthlocal = minimumWidth
		var heightlocal = minimumHeight
		if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {//width exactly
			widthlocal = MeasureSpec.getSize(widthMeasureSpec)
		}
		if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {//height exactly
			heightlocal = MeasureSpec.getSize(heightMeasureSpec)
		}
		if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {//width at most
			widthlocal = MeasureSpec.getSize(widthMeasureSpec)
		}
		if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {//height at most
			heightlocal = MeasureSpec.getSize(heightMeasureSpec)
		}
		setMeasuredDimension(widthlocal, heightlocal)
	}

	private fun setMeasuredDimension(measuredWidth: Int, measuredHeight: Int) {
		this.measuredWidth = measuredWidth
		this.measuredHeight = measuredHeight
	}

	fun draw(c: Canvas) {
		Log.i("draw called", "${c.width} == $measuredWidth, ${c.height} = $measuredHeight");
		if(c.width != measuredWidth || c.height != measuredHeight){
			requestLayout()
		}
		onDraw(c)
	}

	open fun onDraw(c: Canvas) {
	}

	//java implemented functions
	override fun paint(g: Graphics) {
		//Log.i("paint called", this::class+"")
				
// TODO Auto-generated method stub
		if(g == null){return; }
		super.paint(g)
		draw(Canvas(g))
	}

	fun actionPerformed(e: ActionEvent?) {
		throw UnsupportedOperationException("unable to handle click events")
	}
//-------------------------------------------------
	/**
	 * A MeasureSpec encapsulates the layout requirements passed from parent to child.
	 * Each MeasureSpec represents a requirement for either the width or the height.
	 * A MeasureSpec is comprised of a size and a mode. There are three possible
	 * modes:
	 * <dl>
	 * <dt>UNSPECIFIED</dt>
	 * <dd>
	 * The parent has not imposed any constraint on the child. It can be whatever size
	 * it wants.
	 * </dd>
	 *
	 * <dt>EXACTLY</dt>
	 * <dd>
	 * The parent has determined an exact size for the child. The child is going to be
	 * given those bounds regardless of how big it wants to be.
	 * </dd>
	 *
	 * <dt>AT_MOST</dt>
	 * <dd>
	 * The child can be as large as it wants up to the specified size.
	 * </dd>
	 * </dl>
	 *
	 * MeasureSpecs are implemented as ints to reduce object allocation. This class
	 * is provided to pack and unpack the &lt;size, mode&gt; tuple into the int.
	 */
	object MeasureSpec {
		private val MODE_SHIFT = 30
		private val MODE_MASK = 0x3 shl MODE_SHIFT
		/**
		 * Measure specification mode: The parent has not imposed any constraint
		 * on the child. It can be whatever size it wants.
		 */
		val UNSPECIFIED = 0 shl MODE_SHIFT
		/**
		 * Measure specification mode: The parent has determined an exact size
		 * for the child. The child is going to be given those bounds regardless
		 * of how big it wants to be.
		 */
		val EXACTLY = 1 shl MODE_SHIFT
		/**
		 * Measure specification mode: The child can be as large as it wants up
		 * to the specified size.
		 */
		val AT_MOST = 2 shl MODE_SHIFT

		/**
		 * Creates a measure specification based on the supplied size and mode.
		 *
		 * The mode must always be one of the following:
		 * <ul>
		 * <li>{@link android.view.View.MeasureSpec#UNSPECIFIED}</li>
		 * <li>{@link android.view.View.MeasureSpec#EXACTLY}</li>
		 * <li>{@link android.view.View.MeasureSpec#AT_MOST}</li>
		 * </ul>
		 *
		 * <p><strong>Note:</strong> On API level 17 and lower, makeMeasureSpec's
		 * implementation was such that the order of arguments did not matter
		 * and overflow in either value could impact the resulting MeasureSpec.
		 * {@link android.widget.RelativeLayout} was affected by this bug.
		 * Apps targeting API levels greater than 17 will get the fixed, more strict
		 * behavior.</p>
		 *
		 * @param size the size of the measure specification
		 * @param mode the mode of the measure specification
		 * @return the measure specification based on size and mode
		 */
		fun makeMeasureSpec(
			size: Int,
			mode: Int
		): Int {
			return (size and MODE_MASK.inv()) or (mode and MODE_MASK)
		}

		/**
		 * Like {@link #makeMeasureSpec(int, int)}, but any spec with a mode of UNSPECIFIED
		 * will automatically get a size of 0. Older apps expect this.
		 *
		 * @hide internal use only for compatibility with system widgets and older apps
		 */
		fun makeSafeMeasureSpec(size: Int, mode: Int): Int {
			return makeMeasureSpec(size, mode)
		}

		/**
		 * Extracts the mode from the supplied measure specification.
		 *
		 * @param measureSpec the measure specification to extract the mode from
		 * @return {@link android.view.View.MeasureSpec#UNSPECIFIED},
		 * {@link android.view.View.MeasureSpec#AT_MOST} or
		 * {@link android.view.View.MeasureSpec#EXACTLY}
		 */
		fun getMode(measureSpec: Int): Int {
			return (measureSpec and MODE_MASK)
		}

		/**
		 * Extracts the size from the supplied measure specification.
		 *
		 * @param measureSpec the measure specification to extract the size from
		 * @return the size in pixels defined in the supplied measure specification
		 */
		fun getSize(measureSpec: Int): Int {
			return (measureSpec and MODE_MASK.inv())
		}

		internal fun adjust(measureSpec: Int, delta: Int): Int {
			val mode = getMode(measureSpec)
			var size = getSize(measureSpec)
			if (mode == UNSPECIFIED) {
// No need to adjust size for UNSPECIFIED mode.
				return makeMeasureSpec(size, UNSPECIFIED)
			}
			size += delta
			if (size < 0) {
				Log.e(
					"android.view.View", ("MeasureSpec.adjust: new size would be negative! (" + size +
							") spec: " + toString(measureSpec) + " delta: " + delta)
				)
				size = 0
			}
			return makeMeasureSpec(size, mode)
		}

		/**
		 * Returns a String representation of the specified measure
		 * specification.
		 *
		 * @param measureSpec the measure specification to convert to a String
		 * @return a String with the following format: "MeasureSpec: MODE SIZE"
		 */
		fun toString(measureSpec: Int): String? {
			val mode = getMode(measureSpec)
			val size = getSize(measureSpec)
			val sb = StringBuilder("MeasureSpec: ")
			if (mode == UNSPECIFIED)
				sb!!.append("UNSPECIFIED ")
			else if (mode == EXACTLY)
				sb!!.append("EXACTLY ")
			else if (mode == AT_MOST)
				sb!!.append("AT_MOST ")
			else
				sb!!.append(mode).append(" ")
			sb!!.append(size)
			return sb!!.toString()
		}
	}
}