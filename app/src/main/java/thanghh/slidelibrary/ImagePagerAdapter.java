package thanghh.slidelibrary;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;



/**
 * ${CLASS}
 * Created by tryczson on 11/07/2018.
 */

public class ImagePagerAdapter extends PagerAdapter {
    private List<Integer> mArrayList =  new ArrayList<>();
    private Context mContext;

    public ImagePagerAdapter(Context mContext) {
        this.mContext = mContext;

    }




    public void addAll(List<Integer> arrayList) {
        this.mArrayList.clear();
        this.mArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        Log.d("mArrayListGetCount", "getCount: " + mArrayList.size());

        return mArrayList.size();
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.reward_item_slide_market, container, false);

        ImageView imgDisplay = layout.findViewById(R.id.mImageViewItemSlide);
        imgDisplay.setImageResource(mArrayList.get(position));
        container.addView(imgDisplay);
        return imgDisplay;
    }

    @Override
    public void destroyItem(ViewGroup viewPager, int position, Object object) {
        viewPager.removeView((View) object);
    }

}
