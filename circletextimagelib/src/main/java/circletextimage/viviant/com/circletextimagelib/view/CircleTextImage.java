package circletextimage.viviant.com.circletextimagelib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import circletextimage.viviant.com.circletextimagelib.R;
import circletextimage.viviant.com.circletextimagelib.util.CircleTextImageUtil;


/**
 * author：viviant on 2016/7/13 09:08
 * description：A round head, which can be stored on the text, suitable for contact head
 */
public class CircleTextImage extends ImageView {


    private int mCircleColor = Color.RED;//Default background color
    private int mCircleTextColor = Color.WHITE;//Text color
    private boolean mUseRandomBackgroundColor = false;//Use random background color
    private boolean mSubFirstCharacter = false;//Is whether intercept first character

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    private Paint mPaintTextForeground;
    private Paint mPaintTextBackground;
    private static final float DEFAULT_TEXT_SIZE_RATIO = 0.4f;
    private float textSizeRatio = DEFAULT_TEXT_SIZE_RATIO;
    private Paint.FontMetrics mFontMetrics;

    private String mText;
    private int mRadius;
    private int mCenterX;
    private int mCenterY;

    private Context mContext;

    public CircleTextImage(Context context) {
        super(context);
        init();
    }

    public CircleTextImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public CircleTextImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.circletextview);
        mCircleColor = typedArray.getColor(R.styleable.circletextview_circle_color, Color.RED);
        mCircleTextColor = typedArray.getColor(R.styleable.circletextview_circle_text_color, Color.WHITE);
        mUseRandomBackgroundColor = typedArray.getBoolean(R.styleable.circletextview_random_color, false);
        mSubFirstCharacter = typedArray.getBoolean(R.styleable.circletextview_sub_first_character, false);
        this.mContext  = context;

        typedArray.recycle();
    }

    private void init() {

        mPaintTextForeground = new Paint();
        mPaintTextForeground.setColor(mCircleTextColor);
        mPaintTextForeground.setAntiAlias(true);
        mPaintTextForeground.setTextAlign(Paint.Align.CENTER);

        mPaintTextBackground = new Paint();
        mPaintTextBackground.setColor(mCircleTextColor);
        mPaintTextBackground.setAntiAlias(true);
        mPaintTextBackground.setStyle(Paint.Style.FILL);

        if (mUseRandomBackgroundColor) {
            mPaint.setColor(Color.parseColor(CircleTextImageUtil.getInstance().getRandomColor()));
        } else {
            mPaint.setColor(mCircleColor);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100 * 2;
        int desiredHeight = 100 * 2;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (null != mText && !mText.trim().equals("")) {
            mPaintTextForeground.setTextSize(textSizeRatio * 2 * 100);
            int realSize = (int) mPaintTextForeground.measureText(mText,0, mText.length()) + 60;


            if (realSize < 200) {
                realSize = 100 * 2;
            }

            //Measure Width
            if (widthMode == MeasureSpec.EXACTLY) {
                //Must be this size
                width = widthSize;
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                width = realSize;
            } else {
                //Be whatever you want
                width = realSize;
            }

            //Measure Height
            if (heightMode == MeasureSpec.EXACTLY) {
                //Must be this size
                height = heightSize;
            } else if (heightMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                height = realSize;
            } else {
                //Be whatever you want
                height = realSize;
            }
        } else {
            //Measure Width
            if (widthMode == MeasureSpec.EXACTLY) {
                //Must be this size
                width = widthSize;
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                width = Math.min(desiredWidth, widthSize);
            } else {
                //Be whatever you want
                width = desiredWidth;
            }

            //Measure Height
            if (heightMode == MeasureSpec.EXACTLY) {
                //Must be this size
                height = heightSize;
            } else if (heightMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                height = Math.min(desiredHeight, heightSize);
            } else {
                //Be whatever you want
                height = desiredHeight;
            }

        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //get padding
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        //deal padding
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;

        if (null != mText && !mText.trim().equals("")) {
            drawText(canvas);
        } else {
            canvas.drawCircle(width / 2, height / 2, radius, mPaint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int contentWidth = w - paddingLeft - getPaddingRight();
        int contentHeight = h - paddingTop - getPaddingBottom();
        mRadius = contentWidth < contentHeight ? contentWidth / 2 : contentHeight / 2;
        mCenterX = paddingLeft + mRadius;
        mCenterY = paddingTop + mRadius;
        refreshTextSizeConfig();
    }

    private void drawText(Canvas canvas) {
        mPaintTextBackground.setColor(mCircleTextColor);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
        canvas.drawText(mText, 0, mText.length(), mCenterX, mCenterY + Math.abs(mFontMetrics.top + mFontMetrics.bottom) / 2, mPaintTextForeground);

    }

    private void refreshTextSizeConfig() {
        mPaintTextForeground.setTextSize(textSizeRatio * 2 * 100);
        mFontMetrics = mPaintTextForeground.getFontMetrics();

    }

    public void setText4CircleImage(String text) {
        if (mSubFirstCharacter) {
            this.mText = CircleTextImageUtil.getInstance().subFirstCharacter(text);
        } else {
            this.mText = text;
        }
        invalidate();

    }

}
