package com.betelgeuse.recyclewiewdraggablecell;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    RecyclerView              recyclerView;
    RecyclerviewAdapter       recyclerviewAdapter;
    ArrayList<Item>           arrayList = new ArrayList<>();
    LinearLayoutManager       linearLayoutManager;
    LayoutAnimationController layoutAnimationController;
    Paint                     p         = new Paint();
    Bitmap                    icon;
    LinearLayout              linearLayout;


    public void addItemsToArrayList ( ) {
        for (int i = 0; i < 20; i++) {
            arrayList.add(new Item(R.drawable.knight_online, "Data : "+ String.valueOf(i)));
        }

    }

    public void initializers ( ) {
        recyclerviewAdapter = new RecyclerviewAdapter(MainActivity.this, arrayList);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerviewAdapter);

        layoutAnimationController =
                AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_animation_down_to_up);

        recyclerView.setLayoutAnimation(layoutAnimationController);
        swipe();
    }

    private void swipe ( ) {
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove (@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        int position_dragged=viewHolder.getAdapterPosition();
                        int position_target=target.getAdapterPosition();
                        Collections.swap(arrayList,position_dragged,position_target);
                        recyclerviewAdapter.itemMove(position_dragged,position_target);
                        return false;
                    }

                    @Override
                    public void onSwiped (@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        final int position = viewHolder.getAdapterPosition();
                        if (direction == ItemTouchHelper.LEFT) {
                            final Item deletemodel = arrayList.get(position);
                            recyclerviewAdapter.removeItem(position);
                            Snackbar snackbar =
                                    Snackbar.make(MainActivity.this.findViewById(android.R.id.content), getText(R.string.app_name), Snackbar.LENGTH_LONG);
                            snackbar.setAction(getText(R.string.undo), new View.OnClickListener() {
                                @Override
                                public void onClick (View view) {
                                    recyclerviewAdapter.restoreItem(deletemodel, position);
                                }
                            });
                            snackbar.setActionTextColor(Color.YELLOW);
                            snackbar.show();
                        } else {
                            recyclerviewAdapter.itemAction(position);
                        }
                    }

                    @Override
                    public void onChildDraw (@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                            View itemView = viewHolder.itemView;
                            float height = (float) itemView.getBottom() - (float) itemView.getTop();
                            float width = height / 3;
                            if (dX > 0) {
                                p.setColor(Color.parseColor("#388E3C"));
                                RectF background =
                                        new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                                c.drawRect(background, p);
                                icon =
                                        BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_edit);
                                RectF icon_dest =
                                        new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                                c.drawBitmap(icon, null, icon_dest, p);
                            } else {
                                p.setColor(Color.parseColor("#D32F2F"));
                                RectF background =
                                        new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                                c.drawRect(background, p);
                                icon =
                                        BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_delete);
                                RectF icon_dest =
                                        new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                                c.drawBitmap(icon, null, icon_dest, p);
                            }
                        }

                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemsToArrayList();
        initializers();


    }
}