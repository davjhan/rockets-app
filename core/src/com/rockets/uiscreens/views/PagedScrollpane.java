package com.rockets.uiscreens.views;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.rockets.constants.Display;
import com.rockets.uiscreens.managers.SelectionManager;

import java.util.ArrayList;
import java.util.List;

public class PagedScrollpane<T> extends ScrollPane {
    private boolean wasPanDragFling = false;

    private float pageSpacing;
    private float sidePad;
    private Table content;
    private int selectedIndex;
    private final List<T> dataList = new ArrayList<>();
    private SelectionManager<T> selectionManager;
    private float FLING_BIAS_MAG = 48;
    private float lastScrollX = 0;
    private PagedScrollpaneListener listener;
    public PagedScrollpane(SelectionManager<T> selectionManager) {
        super(null);
        content = new Table();
        content.defaults().space(50);
        content.padBottom(96);
        setWidget(content);
        this.selectionManager = selectionManager;
    }

    public void addPages (Actor... pages) {
        for (Actor page : pages) {
            content.add(page).expandY().fillY();
        }
    }

    public void addPage (Actor page, T data) {
        content.add(page).fillY();
        dataList.add(data);
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        if (wasPanDragFling && !isPanning() && !isDragging() && !isFlinging()) {
            wasPanDragFling = false;
            scrollToPage();
            if(listener != null){
                listener.onStateChanged(false);
            }
        } else {
            if (isPanning() || isDragging() || isFlinging()) {
                if(!wasPanDragFling && listener != null){
                    listener.onStateChanged(true);
                }
                wasPanDragFling = true;
            }
        }
    }

    @Override
    public void layout() {
        super.layout();
        if(content.getChildren().size  >0 ) {
            sidePad = Display.SCREEN_WIDTH/2-content.getChildren().get(0).getWidth()/2;
            content.padLeft(sidePad);
            content.padRight(sidePad);
        }
    }

    @Override
    public void setWidth (float width) {
        super.setWidth(width);
        if (content != null) {
            for (Cell cell : content.getCells()) {
                cell.width(width);
            }
            content.invalidate();
        }
    }

    public void setPageSpacing (float pageSpacing) {
        this.pageSpacing = pageSpacing;
        if (content != null) {
            content.defaults().space(pageSpacing);
            for (Cell cell : content.getCells()) {
                cell.space(pageSpacing);
            }
            content.invalidate();
        }
    }

    private void scrollToPage () {

        final float flingBias = Math.signum(getScrollX()-lastScrollX)* FLING_BIAS_MAG;
        final float width = getWidth();
        final float scrollX = getScrollX()+flingBias;
        final float maxX = getMaxX();

        Array<Actor> pages = content.getChildren();
        float pageX = 0;
        float closestPageX = 0;
        float smallestDistToMid = 99999;
        float distToMid = 0;
        int index = 0;
        int chosenIndex = 0;
        if (pages.size > 0) {
            for (Actor a : pages) {
                pageX = a.getX()-sidePad;
                distToMid = Math.abs(scrollX-pageX);
                if(distToMid < smallestDistToMid){
                    smallestDistToMid = distToMid;
                    closestPageX = pageX;
                    chosenIndex = index;
                }else{
                    break;
                }
                index ++;
            }
            setScrollX(MathUtils.clamp(closestPageX, 0, maxX));
            lastScrollX = closestPageX;
            selectedIndex = chosenIndex;
            notifySelection();
        }
    }

    private void notifySelection() {
        selectionManager.select(dataList.get(selectedIndex));
    }

    public void select(T data) {
        selectedIndex = dataList.indexOf(data);
        Actor page = content.getChildren().get(selectedIndex);
        float newX = page.getX()-sidePad;
        setScrollX(MathUtils.clamp(newX, 0, getMaxX()));
        notifySelection();
    }
    public void selectNoAnimate(T data) {
        select(data);
        updateVisualScroll();
    }
    public void addPagedScrollpaneListener(PagedScrollpaneListener pageScrollpaneListener){
        this.listener = pageScrollpaneListener;
    }
    public static interface PagedScrollpaneListener {
        void onStateChanged(boolean isMoving);
    }
}