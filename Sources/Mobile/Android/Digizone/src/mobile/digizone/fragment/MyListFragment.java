package mobile.digizone.fragment;

import java.util.List;

import mobile.digizone.R;
import mobile.digizone.activity.Detail;
import mobile.digizone.loader.ImageLoader;
import mobile.digizone.loader.SpotLoader;
import mobile.digizone.parser.SpotParser.SpotHandler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

// Định nghĩa lại 1 List Fragment theo ý mình
public class MyListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<SpotHandler>>
{
	
	private ListViewAdapter listViewAdapter;
	
	protected List<SpotHandler> list;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.list, container, false);
	}
	
	// Khi activity đã được tạo xong
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null)
		{
			// Đặt bộ chuyển đổi list vào list fragment
			listViewAdapter = new ListViewAdapter();
			setListAdapter(listViewAdapter);
			
			// Đổ list vào
			getLoaderManager().initLoader(0, null, this);
		}
	}
	
	// Khi nhấn vào list item, nhảy qua trang Detail
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		SpotHandler item = (SpotHandler) getListAdapter().getItem(position);
		Bundle args = new Bundle();
		args.putString("img1", item.getImg1());
		args.putString("img2", item.getImg2());
		args.putString("img3", item.getImg3());
		args.putString("img4", item.getImg4());
		args.putString("img5", item.getImg5());
		args.putString("name", item.getName());
		args.putInt("vote", item.getVote());
		args.putString("address", item.getAddress());
		args.putString("short_detail", item.getShortDetail());
		args.putString("full_detail", item.getFullDetail());
		args.putString("email", item.getEmail());
		args.putString("phone", item.getPhone());
		args.putString("facebook", item.getFacebook());
		args.putDouble("latitude", item.getLatitude());
		args.putDouble("longitude", item.getLongitude());
		
		Fragment detail = Detail.newInstance();
		detail.setArguments(args);
		getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainview, detail).commit();
	}
	
	public Loader<List<SpotHandler>> onCreateLoader(int arg0, Bundle arg1)
	{
		if (getActivity() != null)
		{
			return new SpotLoader(getActivity(), list);
		}
		else
		{
			return null;
		}
	}
	
	public void onLoadFinished(Loader<List<SpotHandler>> arg0, List<SpotHandler> data)
	{
		listViewAdapter.setData(data);
	}
	
	public void onLoaderReset(Loader<List<SpotHandler>> arg0)
	{
		listViewAdapter.setData(null);
	}
	
	private class ListViewAdapter extends ArrayAdapter<SpotHandler>
	{
		
		public ListViewAdapter()
		{
			super(getActivity(), android.R.layout.simple_list_item_2);
		}
		
		public void setData(List<SpotHandler> spots)
		{
			clear();
			if (spots != null)
			{
				for (SpotHandler spot : spots)
				{
					add(spot);
				}
			}
		}
		
		// Đổ dữ liệu vào list (tự thiết kế)
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if (view == null)
			{
				view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item, parent, false);
			}
			
			SpotHandler item = getItem(position);
			new ImageLoader(getActivity()).displayImage(item.getImg1(), (ImageView) view.findViewById(R.id.ivThumbnail), R.drawable.no_image);
			((RatingBar) view.findViewById(R.id.rtbVote)).setRating(item.getVote());
			((TextView) view.findViewById(R.id.tvTitle)).setText(item.getName());
			((TextView) view.findViewById(R.id.tvAddress)).setText("Địa chỉ: " + item.getAddress());
			((TextView) view.findViewById(R.id.tvDescription)).setText(item.getShortDetail());
			
			return view;
		}
	}
}