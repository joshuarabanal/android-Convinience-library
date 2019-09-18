package android.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.MeasureSpec;

public class View extends Component {
	protected Component parentContainer = null;
	private int defaultWidth = 0;
	private int defaultHeight = 0;
	private int defaultPadding = 0;
	private int defaultLayoutMargin = 0;
	protected int minimumWidth = 0;
	protected int minimumHeight = 0;
	private int measuredWidth = 0;
		public int getMeasuredWidth() { return measuredHeight; }
	private int measuredHeight = 0;
		public int getMeasuredHeight() { return measuredHeight; }
	private int paddingTop = 0;
	private int paddingBottom = 0;
	private int paddingLeft = 0;
	private int paddingRight = 0;
	private int marginTop = 0;
	private int marginBottom = 0;
	private int marginLeft = 0;
	private int marginRight = 0;
	/**
	 * positioning of this view relative to parents
	 */
	protected int top = 0;
	protected int left = 0;
	private int  width_ours = 0;
	private int height_ours = 0;
	
	
	public View(Context context, AttributeSet attrs, int defaultStyleAttr) {
		if (attrs != null) {
			int[] set = new int[] {
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
			};
			TypedArray a = context.obtainStyledAttributes(
				attrs,
				set
			);
			minimumWidth = a.getDimensionPixelSize(0, defaultWidth);
			minimumHeight = a.getDimensionPixelSize(1, defaultHeight);
			int padding = a.getDimensionPixelSize(2, defaultPadding);
			paddingTop = a.getDimensionPixelSize(3, 0);
			paddingBottom = a.getDimensionPixelSize(4, 0);
			paddingLeft = a.getDimensionPixelSize(5, 0);
			paddingRight = a.getDimensionPixelSize(6, 0);
			int margin = a.getDimensionPixelSize(7, defaultLayoutMargin);
			marginTop = a.getDimensionPixelSize(8, margin);
			marginBottom = a.getDimensionPixelSize(9, margin);
			marginLeft = a.getDimensionPixelSize(10, margin);
			marginRight = a.getDimensionPixelSize(11, margin);
			a.recycle();
		}
	}
	
	public void layout(int left, int top, int right, int bottom) {
		this.top = top;
		this.left = left;
		this.setSize(new Dimension(right - left, bottom - top));
		this.setLocation(top, left);
		this.setBounds(new Rectangle(left, top, getWidth(), getHeight()));
	}
	
	public void requestLayout() {
		if(getParent() != null){
			Log.i("request layout called", "request layout called");
			getParent().validate();
		}
		else{
			Log.i("request layout called", "unable due to null parent");
		}
	}
	
	protected void onMeasure(
			int widthMeasureSpec,
			int heightMeasureSpec
		) {
			int widthlocal = minimumWidth;
			int heightlocal = minimumHeight;
			if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {//width exactly
				widthlocal = MeasureSpec.getSize(widthMeasureSpec);
			}
			if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {//height exactly
				heightlocal = MeasureSpec.getSize(heightMeasureSpec);
			}
			if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {//width at most
				widthlocal = MeasureSpec.getSize(widthMeasureSpec);
			}
			if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {//height at most
				heightlocal = MeasureSpec.getSize(heightMeasureSpec);
			}
			setMeasuredDimension(widthlocal, heightlocal);
		}
	
	private void setMeasuredDimension(int measuredWidth, int measuredHeight) {
		this.measuredWidth = measuredWidth;
		this.measuredHeight = measuredHeight;
	}
	
	public void draw(Canvas c) {
		Log.i("draw called", "${c.width} == $measuredWidth, ${c.height} = $measuredHeight");
		if(c.getWidth() != measuredWidth || c.getHeight() != measuredHeight){
			requestLayout();
		}
		onDraw(c);
	}
	
	public void onDraw( Canvas c) {
	}
	
	//java implemented functions
	public void  paint(Graphics g) {
			//Log.i("paint called", this::class+"")
					
	// TODO Auto-generated method stub
			if(g == null){return; }
			super.paint(g);
			draw(new Canvas(g));
	}
	
	public void actionPerformed(ActionEvent e) {
		throw new UnsupportedOperationException("unable to handle click events");
	}
	
	
	
	
	//----------------------------------------------------------------
	
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
	public static class MeasureSpec {
		private static int MODE_SHIFT = 30;
		private static int MODE_MASK = 0x3 << MODE_SHIFT;
		/**
		 * Measure specification mode: The parent has not imposed any constraint
		 * on the child. It can be whatever size it wants.
		 */
		static int UNSPECIFIED = 0 << MODE_SHIFT;
		/**
		 * Measure specification mode: The parent has determined an exact size
		 * for the child. The child is going to be given those bounds regardless
		 * of how big it wants to be.
		 */
		static int EXACTLY = 1 << MODE_SHIFT;
		/**
		 * Measure specification mode: The child can be as large as it wants up
		 * to the specified size.
		 */
		static int AT_MOST = 2 << MODE_SHIFT;

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
		public static int makeMeasureSpec(int size, int mode) {
			return (size & ~MODE_MASK) | (mode & MODE_MASK);
		}

		/**
		 * Like {@link #makeMeasureSpec(int, int)}, but any spec with a mode of UNSPECIFIED
		 * will automatically get a size of 0. Older apps expect this.
		 *
		 * @hide internal use only for compatibility with system widgets and older apps
		 */
		public static int makeSafeMeasureSpec(int size, int mode) {
			return makeMeasureSpec(size, mode);
		}

		/**
		 * Extracts the mode from the supplied measure specification.
		 *
		 * @param measureSpec the measure specification to extract the mode from
		 * @return {@link android.view.View.MeasureSpec#UNSPECIFIED},
		 * {@link android.view.View.MeasureSpec#AT_MOST} or
		 * {@link android.view.View.MeasureSpec#EXACTLY}
		 */
		public static int getMode(int measureSpec) {
			return (measureSpec & MODE_MASK);
		}

		/**
		 * Extracts the size from the supplied measure specification.
		 *
		 * @param measureSpec the measure specification to extract the size from
		 * @return the size in pixels defined in the supplied measure specification
		 */
		public static int getSize(int measureSpec) {
			return (measureSpec & ~MODE_MASK);
		}

		private static int adjust(int measureSpec, int delta) {
			int mode = getMode(measureSpec);
			int size = getSize(measureSpec);
			if (mode == UNSPECIFIED) {
// No need to adjust size for UNSPECIFIED mode.
				return makeMeasureSpec(size, UNSPECIFIED);
			}
			size += delta;
			if (size < 0) {
				Log.e(
					"android.view.View", ("MeasureSpec.adjust: new size would be negative! (" + size +
							") spec: " +measureSpec + " delta: " + delta)
				);
				size = 0;
			}
			return makeMeasureSpec(size, mode);
		}

		/**
		 * Returns a String representation of the specified measure
		 * specification.
		 *
		 * @param measureSpec the measure specification to convert to a String
		 * @return a String with the following format: "MeasureSpec: MODE SIZE"
		 */
		public static String toString(int measureSpec) {
			int mode = getMode(measureSpec);
			int size = getSize(measureSpec);
			StringBuilder sb = new StringBuilder("MeasureSpec: ");
			if (mode == UNSPECIFIED)
				sb.append("UNSPECIFIED ");
			else if (mode == EXACTLY)
				sb.append("EXACTLY ");
			else if (mode == AT_MOST)
				sb.append("AT_MOST ");
			else
				sb.append(mode).append(" ");
			sb.append(size);
			return sb.toString();
		}
	}

	
}
