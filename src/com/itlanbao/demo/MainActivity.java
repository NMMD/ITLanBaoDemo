package com.itlanbao.demo;
 
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.birdslove.android.jni.ImageBlur;
import com.itlanbao.demo.fancy.FancyCoverFlow;
import com.itlanbao.demo.fancy.FancyCoverFlowAdapter;
import com.itlanbao.demo.fancy.RoundedImageView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class MainActivity extends  Activity {


	private VoiceAdapter mViewGroupAdapter;
	private FancyCoverFlow fancyCoverFlow;

	private TextView sure;// 确定进入下一步按钮
	private ArrayList<VoiceUser> arrayListVoice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initImageLoader(this);
		arrayListVoice = new ArrayList<VoiceUser>();
		
		VoiceUser mVoiceUser1 = new VoiceUser();
		mVoiceUser1.setImageUrl("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a2a99ca7d30735fa8ef048b9ae500f9f/060828381f30e924b128a28d4b086e061c95f7f9.jpg");
		mVoiceUser1.setIndex("1"); 
		arrayListVoice.add(mVoiceUser1);
		
		VoiceUser mVoiceUser2 = new VoiceUser();
		mVoiceUser2.setImageUrl("http://b.hiphotos.baidu.com/image/pic/item/08f790529822720ea5d058ba7ccb0a46f21fab50.jpg");
		mVoiceUser2.setIndex("2"); 
		arrayListVoice.add(mVoiceUser2);
		
		VoiceUser mVoiceUser3 = new VoiceUser();
		mVoiceUser3.setImageUrl("http://e.hiphotos.baidu.com/image/pic/item/e7cd7b899e510fb34395d1c3de33c895d0430cd1.jpg");
		mVoiceUser3.setIndex("3"); 
		arrayListVoice.add(mVoiceUser3);
		
		VoiceUser mVoiceUser4 = new VoiceUser();
		mVoiceUser4.setImageUrl("http://img2.iqilu.com/ed/10/05/07/13/31_100507093116_1.jpg");
		mVoiceUser4.setIndex("4"); 
		arrayListVoice.add(mVoiceUser4);
		
		VoiceUser mVoiceUser5 = new VoiceUser();
		mVoiceUser5.setImageUrl("http://att2.citysbs.com/hangzhou/sns01/forum/2010/10/09-10/20101009_42172338386c23dddb03zQ695asbeKu4.jpg");
		mVoiceUser5.setIndex("5"); 
		arrayListVoice.add(mVoiceUser5);
		
		
		VoiceUser mVoiceUser6 = new VoiceUser();
		mVoiceUser6.setImageUrl("http://img.taopic.com/uploads/allimg/111129/6309-11112921305621.jpg");
		mVoiceUser6.setIndex("6"); 
		arrayListVoice.add(mVoiceUser6);
		
		VoiceUser mVoiceUser7 = new VoiceUser();
		mVoiceUser7.setImageUrl("http://img.taopic.com/uploads/allimg/111125/57998-11112506241028.jpg");
		mVoiceUser7.setIndex("7"); 
		arrayListVoice.add(mVoiceUser7);
		
		VoiceUser mVoiceUser8 = new VoiceUser();
		mVoiceUser8.setImageUrl("http://image.tianjimedia.com/uploadImages/2015/159/14/660V43E2910H.jpg");
		mVoiceUser8.setIndex("8"); 
		arrayListVoice.add(mVoiceUser8);
		
 
		
		
		fancyCoverFlow = (FancyCoverFlow)findViewById(R.id.fancyCoverFlow);
		sure = (TextView) findViewById(R.id.sure);

		mViewGroupAdapter = new VoiceAdapter();
		fancyCoverFlow.setAdapter(mViewGroupAdapter);
		int arg = Integer.MAX_VALUE/2 - 3;  
		fancyCoverFlow.setSelection(arg);
		
		fancyCoverFlow.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				currentIndex = position; 
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		fancyCoverFlow
				.setOnItemClickListener(new FancyCoverFlow.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub  
						
					}
				});

		sure.setOnClickListener(new TextView.OnClickListener() {

			@Override
			public void onClick(View v) {
 

			}
		});
		
	}
	/** 初始化ImageLoader */
    public static void initImageLoader(Context context) {
        String filePath = Environment.getExternalStorageDirectory() +
                "/Android/data/" + context.getPackageName() + "/cache/";

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, filePath);// 获取到缓存的目录地址
        Log.d("cacheDir", cacheDir.getPath());
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                        // .memoryCacheExtraOptions(480, 800) // max width, max
                        // height，即保存的每个缓存文件的最大长宽
                        // .discCacheExtraOptions(480, 800, CompressFormat.JPEG,
                        // 75, null) // Can slow ImageLoader, use it carefully
                        // (Better don't use it)设置缓存的详细信息，最好不要设置这个
                        .threadPoolSize(5)// 线程池内加载的数量
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .denyCacheImageMultipleSizesInMemory()
                        .memoryCache(new WeakMemoryCache())
                        // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024
                        // * 1024)) // You can pass your own memory cache
                        // implementation你可以通过自己的内存缓存实现
                        .memoryCacheSize(2 * 1024 * 1024)
                        // /.discCacheSize(50 * 1024 * 1024)
                        .discCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5
                                                                               // 加密
                        // .discCacheFileNameGenerator(new
                        // HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // .discCacheFileCount(100) //缓存的File数量
                        .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
                        .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                        .imageDownloader(new BaseImageDownloader(context, 5 *
                                1000, 30 * 1000)) // connectTimeout (5 s),
                        // readTimeout(30)// 超时时间
                        .writeDebugLogs() // Remove for release app
                        .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }

     
	 public int dp2px(float dipValue) {
	     final float scale = getResources().getDisplayMetrics().density;
		 return (int) (dipValue * scale + 0.5f);
	 }
	
	private int currentIndex = 0;

	class VoiceAdapter extends FancyCoverFlowAdapter  {
		private int mItemWidth = 0;
		private int mItemHeight = 0;

		private AnimationDrawable currentAnimDrawable;
		private Drawable defaultDrawable;

		protected ImageLoader imageLoader;
		protected DisplayImageOptions options;
		
		public VoiceAdapter() { 
			// 如果当前item正在播放，则设置设置播放动画
	 
			mItemWidth = dp2px(220);
			mItemHeight = dp2px(290);
			imageLoader = ImageLoader.getInstance();
			options = new DisplayImageOptions.Builder()
					.resetViewBeforeLoading(true).cacheInMemory(true)
					.showImageOnLoading(R.drawable.vist_my_space_bg)
					.showImageOnFail(R.drawable.vist_my_space_bg)
					.showImageForEmptyUri(R.drawable.vist_my_space_bg)
					// .displayer(new RoundedBitmapDisplayer(0))
					.cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
					.build();

		}
 
		@Override  
		public int getCount(){   
		    return Integer.MAX_VALUE;  
		}  

		@Override
		public VoiceUser getItem(int position) {
			// TODO Auto-generated method stub
			return arrayListVoice.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/**
		 * 刷新指定item
		 * @param index 用于集合取数据
		 * @param position
		 *            item在listview中的位置
		 */
		public void updateItem(int index,int position) {

			// 获取当前可以看到的item位置
			int visiblePosition = fancyCoverFlow.getFirstVisiblePosition();
			// 如添加headerview后 firstview就是hearderview
			// 所有索引+1 取第一个view
			// View view = listview.getChildAt(index - visiblePosition + 1);
			// 获取点击的view
			View view = fancyCoverFlow.getChildAt(position - visiblePosition);
			TextView selectBtn = (TextView) view.findViewById(R.id.select_btn);
			// 获取mDataList.set(ids, item);更新的数据
			VoiceUser mVoiceUser = arrayListVoice.get(index);
			if (mVoiceUser.isSelect()) {
				selectBtn.setText("不喜欢" + mVoiceUser.getIndex() + "号");
			} else {
				selectBtn.setText("喜欢" + mVoiceUser.getIndex() + "号");
			}
		}

		class ViewHolder {
			TextView selectBtn;
			RelativeLayout itemView;
			RoundedImageView itemImg;
			RelativeLayout voicePlayView;
			TextView voicePlayBtn;
		}

		 
 

		 
		@Override
		public View getCoverFlowItem(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = View.inflate(MainActivity.this,
						R.layout.view_cover_flow_item, null);
				viewHolder = new ViewHolder();
				viewHolder.itemView = (RelativeLayout) convertView
						.findViewById(R.id.item_img_view);

				viewHolder.selectBtn = (TextView) convertView
						.findViewById(R.id.select_btn);

				viewHolder.voicePlayView = (RelativeLayout) convertView
						.findViewById(R.id.voice_play_view);
				viewHolder.voicePlayBtn = (TextView) convertView
						.findViewById(R.id.voice_play_btn);

				viewHolder.itemImg = (RoundedImageView) convertView
						.findViewById(R.id.item_img);

				convertView.setLayoutParams(new FancyCoverFlow.LayoutParams(
						mItemWidth, mItemHeight));
				if (convertView != null) {
					convertView.setTag(viewHolder);
				}
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			final RoundedImageView itemImg1 = viewHolder.itemImg;
			final int index = position%arrayListVoice.size(); 
			if(arrayListVoice.size()> index){
				final VoiceUser mVoiceUser = arrayListVoice.get(index);
				if (mVoiceUser != null) { 
					imageLoader.displayImage(mVoiceUser.getImageUrl(), viewHolder.itemImg , options);
//					imageLoader.displayImage(mVoiceUser.getImageUrl(), viewHolder.itemImg , options,new ImageLoadingListener() {
//						
//						@Override
//						public void onLoadingStarted(String arg0, View arg1) {
//							// TODO Auto-generated method stub
//							
//						}
//						
//						@Override
//						public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//							// TODO Auto-generated method stub
//							
//						}
//						
//						@Override
//						public void onLoadingComplete(String arg0, View arg1, Bitmap bitmap) {
//							// TODO Auto-generated method stub
//							if (bitmap != null) {
//								// transparentImageBg.setVisibility(View.VISIBLE);
//								ImageBlur.blurBitMap(bitmap, 13);
//								itemImg1.setImageBitmap(bitmap);
//							}
//						}
//						
//						@Override
//						public void onLoadingCancelled(String arg0, View arg1) {
//							// TODO Auto-generated method stub
//							
//						}
//					});
					 
				}
			}
			

			return convertView;
		}

	}

  
}
