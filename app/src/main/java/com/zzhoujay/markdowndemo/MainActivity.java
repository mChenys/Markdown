package com.zzhoujay.markdowndemo;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.zzhoujay.markdown.MarkDown;
import com.zzhoujay.markdown.method.LongPressLinkMovementMethod;
import com.zzhoujay.markdown.style.LongPressClickableSpan;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        assert mTextView != null;
        mTextView.setMovementMethod(LongPressLinkMovementMethod.getInstance());

        setText(R.raw.cys);
    }

    private void setText(int resId) {
        final InputStream stream = getResources().openRawResource(resId);

        mTextView.post(new Runnable() {
            @Override
            public void run() {
                long time = System.nanoTime();
                Spanned spanned = MarkDown.fromMarkdown(stream, new Html.ImageGetter() {
                    public static final String TAG = "Markdown";

                    @Override
                    public Drawable getDrawable(String source) {
                        Log.d(TAG, "getDrawable() called with: source = [" + source + "]");

                        Drawable drawable;
                        try {
                            drawable = drawableFromUrl(source);
                            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        } catch (Exception e) {
                            Log.w(TAG, "can't get image", e);
                            drawable = new ColorDrawable(Color.LTGRAY);
                            drawable.setBounds(0, 0, mTextView.getWidth() - mTextView.getPaddingLeft() - mTextView.getPaddingRight(), 400);
                        }
                        return drawable;
                    }
                }, mTextView);
                long useTime = System.nanoTime() - time;
                Toast.makeText(getApplicationContext(), "use time: " + useTime + "ns", Toast.LENGTH_LONG).show();
                mTextView.setText(spanned);
            }
        });
    }

    /**
     * 解析url图片
     * @param url
     * @return
     * @throws IOException
     */
    public static Drawable drawableFromUrl(String url) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (mode == Configuration.UI_MODE_NIGHT_NO) {// 当前是白天模式
            menu.add(0, 0x1,        0, "Night Mode");
        }else{
            menu.add(0, 0x1,        0, "Light Mode");
        }
        SubMenu sub = menu.addSubMenu(0, 0x2, 0, "Documents");
        sub.add(0, R.raw.dy,       0, "dy");
        sub.add(0, R.raw.hello,    0, "hello");
        sub.add(0, R.raw.mark,     0, "mark");
        sub.add(0, R.raw.sof,      0, "sof");
        sub.add(0, R.raw.test,     0, "test");
        sub.add(0, R.raw.tt,       0, "tt");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 0x1
                && item.getItemId() != 0x2) {
            setText(item.getItemId());
            return true;
        } else if (item.getItemId() == 0x1) {
//            getResources().getConfiguration().uiMode |= Configuration.UI_MODE_NIGHT_YES; // 开启暗色主题
//            getResources().getConfiguration().uiMode &= ~Configuration.UI_MODE_NIGHT_NO; // 去掉暗色主题的关闭
//            getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
            int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            switch (mode) {
                case Configuration.UI_MODE_NIGHT_NO: // 当前是白天模式,点击切换到夜间模式
                    getResources().getConfiguration().uiMode |= Configuration.UI_MODE_NIGHT_YES;
                    getResources().getConfiguration().uiMode &= ~Configuration.UI_MODE_NIGHT_NO;
                    break;
                case Configuration.UI_MODE_NIGHT_YES: // 当前是夜间模式,点击切换到白天模式
                    getResources().getConfiguration().uiMode |= Configuration.UI_MODE_NIGHT_NO;
                    getResources().getConfiguration().uiMode &= ~Configuration.UI_MODE_NIGHT_YES;
                    break;
            }
            getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
            recreate();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
