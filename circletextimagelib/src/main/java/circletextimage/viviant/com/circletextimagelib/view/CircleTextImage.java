package circletextimage.viviant.com.circletextimagelib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import circletextimage.viviant.com.circletextimagelib.R;


/**
 * author：viviant on 2016/7/13 09:08
 * description：A round head, which can be stored on the text, suitable for contact head
 */
public class CircleTextImage extends ImageView {


    private int mCircleColor = Color.RED;//Default background color
    private int mCircleTextColor = Color.WHITE;//text color
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean useRandomBackgroundColor = false;//use random background color
    private boolean mSubFirstCharacter = false;

    private String text;
    private Paint paintTextForeground;
    private Paint paintTextBackground;
    private static final float DEFAULT_TEXT_SIZE_RATIO = 0.4f;
    private float textSizeRatio = DEFAULT_TEXT_SIZE_RATIO;
    private Paint.FontMetrics fontMetrics;
    private int radius;
    private int centerX;
    private int centerY;

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
        useRandomBackgroundColor = typedArray.getBoolean(R.styleable.circletextview_random_color, false);
        mSubFirstCharacter = typedArray.getBoolean(R.styleable.circletextview_sub_first_character, false);

        typedArray.recycle();
    }

    private void init() {

        paintTextForeground = new Paint();
        paintTextForeground.setColor(mCircleTextColor);
        paintTextForeground.setAntiAlias(true);
        paintTextForeground.setTextAlign(Paint.Align.CENTER);

        paintTextBackground = new Paint();
        paintTextBackground.setColor(mCircleTextColor);
        paintTextBackground.setAntiAlias(true);
        paintTextBackground.setStyle(Paint.Style.FILL);

        if (useRandomBackgroundColor) {
            mPaint.setColor(Color.parseColor(CircleTextImageUtil.getRandomColor()));
        } else {
            mPaint.setColor(mCircleColor);
        }

    }

    public void setText4CircleImage(String text) {
            if (mSubFirstCharacter) {
                this.text = CircleTextImageUtil.subFirstCharacter(text);
            } else {
                this.text = text;
            }
            invalidate();

    }

    private void drawText(Canvas canvas) {
        paintTextBackground.setColor(mCircleTextColor);
        canvas.drawCircle(centerX, centerY, radius, mPaint);
        canvas.drawText(text, 0, text.length(), centerX, centerY + Math.abs(fontMetrics.top + fontMetrics.bottom) / 2, paintTextForeground);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (null != text && !text.trim().equals("")) {
            int realSize = (int)paintTextForeground.measureText(text) + 60;
            if (realSize < 200) {
                realSize = 200;
            }
            if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(realSize, realSize);
            } else if (widthSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(realSize, heightSpecSize);
            } else if (heightSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSpecSize, realSize);
            }
        } else {
            if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(200, 200);
            } else if (widthSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(200, heightSpecSize);
            } else if (heightSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSpecSize, 200);
            }
        }

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

        if (null != text && !text.trim().equals("")) {
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
        radius = contentWidth < contentHeight ? contentWidth / 2 : contentHeight / 2;
        centerX = paddingLeft + radius;
        centerY = paddingTop + radius;
        refreshTextSizeConfig();

    }


    private void refreshTextSizeConfig() {
        paintTextForeground.setTextSize(textSizeRatio * 2 * 100);
        fontMetrics = paintTextForeground.getFontMetrics();

    }

    static class  CircleTextImageUtil{
        /**
         * Get the random color.
         * @return
         */
        private static String getRandomColor() {
            List<String> colorList = new ArrayList<String>();
            colorList.add("#303F9F");
            colorList.add("#FF4081");
            colorList.add("#59dbe0");
            colorList.add("#f57f68");
            colorList.add("#87d288");
            colorList.add("#f8b552");
            colorList.add("#990099");
            colorList.add("#90a4ae");
            colorList.add("#7baaf7");
            colorList.add("#4dd0e1");
            colorList.add("#4db6ac");
            colorList.add("#aed581");
            colorList.add("#fdd835");
            colorList.add("#f2a600");
            colorList.add("#ff8a65");
            colorList.add("#f48fb1");
            colorList.add("#7986cb");
            return colorList.get((int)(Math.random() * 16));
        }

        /**
         * Interception of the first string of characters.
         * @param str
         * @return
         */
        private static String subFirstCharacter(String str) {
            if (Character.isLetter(str.charAt(0))) {
                return Character.toUpperCase(str.charAt(0))+"";
            } else {
                return str.charAt(0) +"";
            }
        }
    }

}
