package com.example.shoppingcart.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

import com.example.shoppingcart.models.Item;

//2022.12.23
// カテゴリーと食材と単位をINNER JOINで結合した表を取り出す

@DatabaseView(
        viewName = "categoryWithIngredientAndUnit",
        value = "SELECT" +
                "  cat.category_id AS category_id," +
                "  cat.category_name AS category_name," +
                "  ing.ingredient_id AS ingredient_id," +
                "  ing.ing_name AS ing_name," +
                "  ing.unit_id AS unit_id ," +
                "  ing.expiration_date AS expiration_date," +
                "  ing.img_url AS img_url," +
                "  u.unit_name AS unit_name," +
                "  SUM(st.quantity) AS quantity_sum"+
                " FROM ing_category AS cat" +
                " INNER JOIN ingredient AS ing" +
                "  ON cat.category_id = ing.category_id" +
                " INNER JOIN unit AS u" +
                "  ON ing.unit_id = u.unit_id" +
                " INNER JOIN stock as st"+
                "  ON st.ingredient_id = ing.ingredient_id" +
                " GROUP BY ing.ingredient_id" +
                " ORDER BY cat.category_id,ing.ingredient_id"
)

public class CategoryWithIngredientAndUnit {
    @ColumnInfo(name = "category_id")
    private int category_id;

    @ColumnInfo(name = "category_name")
    private String category_name;

    @ColumnInfo(name = "ingredient_id")
    private int ingredient_id;

    @ColumnInfo(name = "ing_name")
    private String ing_name;

    @ColumnInfo(name = "unit_id")
    private int unit_id;

    @ColumnInfo(name = "expiration_date")
    private String expiration_date;

    @ColumnInfo(name = "img_url")
    private String img_url;

    @ColumnInfo(name = "unit_name")
    private String unit_name;

    @ColumnInfo(name = "quantity_sum")
    private int quantity_sum;

    public CategoryWithIngredientAndUnit(int category_id, String category_name, int ingredient_id, String ing_name, int unit_id, String expiration_date, String img_url, String unit_name, int quantity_sum) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.ingredient_id = ingredient_id;
        this.ing_name = ing_name;
        this.unit_id = unit_id;
        this.expiration_date = expiration_date;
        this.img_url = img_url;
        this.unit_name = unit_name;
        this.quantity_sum = quantity_sum;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getIng_name() {
        return ing_name;
    }

    public void setIng_name(String ing_name) {
        this.ing_name = ing_name;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }


    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public int getQuantity_sum() {
        return quantity_sum;
    }

    public void setQuantity_sum(int quantity_sum) {
        this.quantity_sum = quantity_sum;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryWithIngredientAndUnit c = (CategoryWithIngredientAndUnit) o;
        return c.category_id == category_id &&
                c.ingredient_id == ingredient_id &&
                c.expiration_date.equals(expiration_date) &&
                c.quantity_sum == quantity_sum;
    }
    public static DiffUtil.ItemCallback<CategoryWithIngredientAndUnit> itemCallback = new DiffUtil.ItemCallback<CategoryWithIngredientAndUnit>() {
        @Override
        public boolean areItemsTheSame(@NonNull CategoryWithIngredientAndUnit oldItem, @NonNull CategoryWithIngredientAndUnit newItem) {
            return oldItem.getIngredient_id() == newItem.getIngredient_id() &&
                   oldItem.getCategory_id() == newItem.getCategory_id()  &&
                   oldItem.getQuantity_sum() == newItem.getQuantity_sum();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CategoryWithIngredientAndUnit oldItem, @NonNull CategoryWithIngredientAndUnit newItem) {
            return oldItem.equals(newItem);
        }
    };
}
