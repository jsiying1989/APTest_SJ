package adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.example.androidtest_sj.R;

import model.ChatData;

public class ChatsArrayAdapter extends ArrayAdapter<ChatData> {

	static {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	public ChatsArrayAdapter(Context context, List<ChatData> objects) {
		super(context, 0, objects);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ChatCell chatCell = new ChatCell();

		LayoutInflater inflater = LayoutInflater.from(getContext());
		convertView = inflater.inflate(R.layout.cell_chat, parent, false);

		chatCell.usernameTextView = (TextView) convertView
				.findViewById(R.id.usernameTextView);
		chatCell.messageTextView = (TextView) convertView
				.findViewById(R.id.messageTextView);
		chatCell.userimageView = (ImageView) convertView
				.findViewById(R.id.userimageView);

		ChatData chatData = getItem(position);

		chatCell.usernameTextView.setText(chatData.username);
		chatCell.messageTextView.setText(chatData.message);
		String url = chatData.avatarURL;

		Bitmap bitmap = getHttpBitmap(url);
		// get url

		chatCell.userimageView.setImageBitmap(toRoundCorner(bitmap, 80.f));

		return convertView;
	}

	/*public void settype(TextView tv, float size) {
		AssetManager mgr = context.getAssets();
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"MachinatoBold.ttf");
		tv.setTypeface(typeFace);
		tv.setTextSize(size);
	}
*/
	private static class ChatCell {
		TextView usernameTextView;
		TextView messageTextView;
		ImageView userimageView;
	}

	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			// Log.d(TAG, url);
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setConnectTimeout(0);
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static Bitmap toRoundCorner(Bitmap bitmap, float pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);

		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		System.out.println("pixels+++++++" + pixels);

		return output;
	}

}
