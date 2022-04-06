package com.betelgeuse.dragrecycleritemtoanothertabview.ui.main;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.betelgeuse.dragrecycleritemtoanothertabview.R;
import com.betelgeuse.dragrecycleritemtoanothertabview.databinding.FragmentMainBinding;
import com.google.android.material.chip.Chip;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements View.OnLongClickListener,View.OnDragListener {

    private  static PlaceholderFragment instance = null;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel       pageViewModel;
    private FragmentMainBinding binding;
    private LinearLayout row ;
    private Chip chipA,chipB;

    public static PlaceholderFragment newInstance (int index) {

            instance = new PlaceholderFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_SECTION_NUMBER, index);
            instance.setArguments(bundle);
            return instance;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView (
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        chipA=binding.chipA; chipB = binding.chipB;
        chipA.setOnLongClickListener(this);
        chipB.setOnLongClickListener(this);
        row = binding.row;
        row.setOnDragListener(this);
        return root;
    }

    @Override
    public void onStart ( ) {
        super.onStart();
    }

    @Override
    public void onDestroyView ( ) {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public boolean onLongClick (View view) {
        ClipData.Item itemClipData = new ClipData.Item((CharSequence) view.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData clipData = new ClipData(view.getTag().toString(), mimeTypes, itemClipData);
        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(clipData, dragShadowBuilder, view, 0);
        view.setVisibility(View.VISIBLE);
        return true;
    }

    @Override
    public boolean onDrag (View view, DragEvent event) {
        if (event.getAction()==DragEvent.ACTION_DROP) {
            View draggableObj = (View) event.getLocalState();
            LinearLayout oldParent = (LinearLayout) draggableObj.getParent();
            oldParent.removeView(draggableObj);
            LinearLayout newParent = ((LinearLayout) view);
            newParent.addView(draggableObj);

        }
        return true;
    }
}