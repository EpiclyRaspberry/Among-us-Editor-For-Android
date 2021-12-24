package club.koupah.epiclyrasp.aue;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView;
import com.bumptech.glide.Glide;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;
import java.io.IOException;
import java.io.InputStream;

public class BasicSettingsActivity extends Activity {
	
	private String playerPrefs_Path = "";
	private double hatNum = 0;
	private double colorNum = 0;
	private double skinNum = 0;
	private double petNum = 0;
	private String output = "";
	private double outputIndex = 0;
	
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	private ArrayList<String> hatList = new ArrayList<>();
	private ArrayList<String> colorList = new ArrayList<>();
	private ArrayList<String> skinList = new ArrayList<>();
	private ArrayList<String> petList = new ArrayList<>();
	private ArrayList<String> playerPrefsList = new ArrayList<>();
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private LinearLayout linear7;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private EditText edittext1;
	private Button button1;
	private ImageView hatimg;
	private Spinner hatSel;
	private ImageView colorimg;
	private Spinner colorSel;
	private ImageView skinimg;
	private Spinner skinSel;
	private ImageView petimg;
	private Spinner petSel;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.basic_settings);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
			}
			else {
				initializeLogic();
			}
		}
		else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		button1 = (Button) findViewById(R.id.button1);
		hatimg = (ImageView) findViewById(R.id.hatimg);
		hatSel = (Spinner) findViewById(R.id.hatSel);
		colorimg = (ImageView) findViewById(R.id.colorimg);
		colorSel = (Spinner) findViewById(R.id.colorSel);
		skinimg = (ImageView) findViewById(R.id.skinimg);
		skinSel = (Spinner) findViewById(R.id.skinSel);
		petimg = (ImageView) findViewById(R.id.petimg);
		petSel = (Spinner) findViewById(R.id.petSel);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().length() > 0) {
					playerPrefsList.set((int)0, edittext1.getText().toString());
				}
				playerPrefsList.set((int)2, colorList.get((int)(colorSel.getSelectedItemPosition())));
				playerPrefsList.set((int)10, hatList.get((int)(hatSel.getSelectedItemPosition())));
				playerPrefsList.set((int)15, skinList.get((int)(skinSel.getSelectedItemPosition())));
				playerPrefsList.set((int)16, petList.get((int)(petSel.getSelectedItemPosition())));
				outputIndex = 0;
				repeat(20){
					output = playerPrefsList.get((int)(outputIndex)).concat(",");
					outputIndex++;
				}
			}
		});
		
		hatSel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				try {
					InputStream hatimgInput = getAssets().open("images/hat/".concat(String.valueOf((long)(_position)).concat(".png"))); 
					Drawable hatimgDraw = Drawable.createFromStream(hatimgInput, null);
					hatimg.setImageDrawable(hatimgDraw);
					hatimgInput.close();
				} catch(IOException ex) {}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		colorSel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				try {
					InputStream colorimgInput = getAssets().open("images/color/".concat(String.valueOf((long)(_position)).concat(".png"))); 
					Drawable colorimgDraw = Drawable.createFromStream(colorimgInput, null);
					colorimg.setImageDrawable(colorimgDraw);
					colorimgInput.close();
				} catch(IOException ex) {}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		skinSel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				/*
Glide.with(getApplicationContext()).load(Uri.parse("file:///android_res/images/skins/".concat(String.valueOf((long)(_position)).concat(".png")))).into(skinimg);
*/
				try {
					InputStream skinimgInput = getAssets().open("images/skin/".concat(String.valueOf((long)(_position)).concat(".png"))); 
					Drawable skinimgDraw = Drawable.createFromStream(skinimgInput, null);
					skinimg.setImageDrawable(skinimgDraw);
					skinimgInput.close();
				} catch(IOException ex) {}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		petSel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				try {
					InputStream petimgInput = getAssets().open("images/pet/".concat(String.valueOf((long)(_position)).concat(".png"))); 
					Drawable petimgDraw = Drawable.createFromStream(petimgInput, null);
					petimg.setImageDrawable(petimgDraw);
					petimgInput.close();
				} catch(IOException ex) {}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		playerPrefs_Path = "/sdcard/Android/data/com.innersloth.spacemafia/files/playerPrefs";
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("test", "lol");
			map.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("test", "lolol");
			map.add(_item);
		}
		
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("test", "red sus");
			map.add(_item);
		}
		
		hatNum = 0;
		skinNum = 0;
		colorNum = 0;
		petNum = 0;
		hatSel.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, hatList));
		colorSel.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, colorList));
		skinSel.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, skinList));
		petSel.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, petList));
		while(hatNum < 115) {
			hatList.add(String.valueOf((long)(hatNum)));
			hatNum++;
			((ArrayAdapter)hatSel.getAdapter()).notifyDataSetChanged();
		}
		while(colorNum < 13) {
			colorList.add(String.valueOf((long)(colorNum)));
			colorNum++;
			((ArrayAdapter)colorSel.getAdapter()).notifyDataSetChanged();
		}
		while(skinNum < 19) {
			skinList.add(String.valueOf((long)(skinNum)));
			skinNum++;
			((ArrayAdapter)skinSel.getAdapter()).notifyDataSetChanged();
		}
		while(petNum < 12) {
			petList.add(String.valueOf((long)(petNum)));
			petNum++;
			((ArrayAdapter)petSel.getAdapter()).notifyDataSetChanged();
		}
		playerPrefsList = new ArrayList<String>(Arrays.asList(FileUtil.readFile(playerPrefs_Path).split(",")));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public class HatSelAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public HatSelAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.spinning, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			
			
			return _view;
		}
	}
	
	public class ColorSelAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public ColorSelAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.spinning, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			
			
			return _view;
		}
	}
	
	public class SkinSelAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public SkinSelAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.spinning, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			
			
			return _view;
		}
	}
	
	public class PetSelAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public PetSelAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.spinning, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}