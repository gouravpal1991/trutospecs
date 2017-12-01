package com.tru2specs.android.objects.responses.product;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PB00471065 on 11/16/2017.
 */

public class Products implements Parcelable{
    private int ID;
    private int ProductId;
    private String StoreId;
    private String StoreName;
    private String ProductName;
    private String Description;
    private String ModelNo;
    private int ProductTypeId;
    private String ProductType;
    private String FrameSizeId;
    private String FrameSize;
    private String FrameColour;
    private String Weight;
    private int WeightGroup;
    private int MaterialId;
    private String Material;
    private int FrameMaterial;
    private int TempleMaterial;
    private String PrescriptionType;
    private String FrameStyleId;
    private String FrameStyle;
    private int FrameStyleSecondary;
    private int FrameShapeId;
    private String FrameShape;
    private String FrameTypeId;
    private String FrameType;
    private String Collection;
    private String ProductWarranty;
    private String Gender;
    private String Height;
    private String Condition;
    private String TempleColour;
    private int Price;
    private int Discount;
    private String DiscountType;
    private int MRP;
    private int MRPAfterDiscount;
    private int IsPower;
    private String SuitsLensType;
    private int isCODAvailable;
    private String Status;
    private int AvailableStatus;
    private int IsApproved;
    private String CreatedBy;
    private String CreatedDate;
    private String ImageURL;

    protected Products(Parcel in) {
        ID = in.readInt();
        ProductId = in.readInt();
        StoreId = in.readString();
        StoreName = in.readString();
        ProductName = in.readString();
        Description = in.readString();
        ModelNo = in.readString();
        ProductTypeId = in.readInt();
        ProductType = in.readString();
        FrameSizeId = in.readString();
        FrameSize = in.readString();
        FrameColour = in.readString();
        Weight = in.readString();
        WeightGroup = in.readInt();
        MaterialId = in.readInt();
        Material = in.readString();
        FrameMaterial = in.readInt();
        TempleMaterial = in.readInt();
        PrescriptionType = in.readString();
        FrameStyleId = in.readString();
        FrameStyle = in.readString();
        FrameStyleSecondary = in.readInt();
        FrameShapeId = in.readInt();
        FrameShape = in.readString();
        FrameTypeId = in.readString();
        FrameType = in.readString();
        Collection = in.readString();
        ProductWarranty = in.readString();
        Gender = in.readString();
        Height = in.readString();
        Condition = in.readString();
        TempleColour = in.readString();
        Price = in.readInt();
        Discount = in.readInt();
        DiscountType = in.readString();
        MRP = in.readInt();
        MRPAfterDiscount = in.readInt();
        IsPower = in.readInt();
        SuitsLensType = in.readString();
        isCODAvailable = in.readInt();
        Status = in.readString();
        AvailableStatus = in.readInt();
        IsApproved = in.readInt();
        CreatedBy = in.readString();
        CreatedDate = in.readString();
        ImageURL = in.readString();
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getModelNo() {
        return ModelNo;
    }

    public void setModelNo(String modelNo) {
        ModelNo = modelNo;
    }

    public int getProductTypeId() {
        return ProductTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        ProductTypeId = productTypeId;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getFrameSizeId() {
        return FrameSizeId;
    }

    public void setFrameSizeId(String frameSizeId) {
        FrameSizeId = frameSizeId;
    }

    public String getFrameSize() {
        return FrameSize;
    }

    public void setFrameSize(String frameSize) {
        FrameSize = frameSize;
    }

    public String getFrameColour() {
        return FrameColour;
    }

    public void setFrameColour(String frameColour) {
        FrameColour = frameColour;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public int getWeightGroup() {
        return WeightGroup;
    }

    public void setWeightGroup(int weightGroup) {
        WeightGroup = weightGroup;
    }

    public int getMaterialId() {
        return MaterialId;
    }

    public void setMaterialId(int materialId) {
        MaterialId = materialId;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public int getFrameMaterial() {
        return FrameMaterial;
    }

    public void setFrameMaterial(int frameMaterial) {
        FrameMaterial = frameMaterial;
    }

    public int getTempleMaterial() {
        return TempleMaterial;
    }

    public void setTempleMaterial(int templeMaterial) {
        TempleMaterial = templeMaterial;
    }

    public String getPrescriptionType() {
        return PrescriptionType;
    }

    public void setPrescriptionType(String prescriptionType) {
        PrescriptionType = prescriptionType;
    }

    public String getFrameStyleId() {
        return FrameStyleId;
    }

    public void setFrameStyleId(String frameStyleId) {
        FrameStyleId = frameStyleId;
    }

    public String getFrameStyle() {
        return FrameStyle;
    }

    public void setFrameStyle(String frameStyle) {
        FrameStyle = frameStyle;
    }

    public int getFrameStyleSecondary() {
        return FrameStyleSecondary;
    }

    public void setFrameStyleSecondary(int frameStyleSecondary) {
        FrameStyleSecondary = frameStyleSecondary;
    }

    public int getFrameShapeId() {
        return FrameShapeId;
    }

    public void setFrameShapeId(int frameShapeId) {
        FrameShapeId = frameShapeId;
    }

    public String getFrameShape() {
        return FrameShape;
    }

    public void setFrameShape(String frameShape) {
        FrameShape = frameShape;
    }

    public String getFrameTypeId() {
        return FrameTypeId;
    }

    public void setFrameTypeId(String frameTypeId) {
        FrameTypeId = frameTypeId;
    }

    public String getFrameType() {
        return FrameType;
    }

    public void setFrameType(String frameType) {
        FrameType = frameType;
    }

    public String getCollection() {
        return Collection;
    }

    public void setCollection(String collection) {
        Collection = collection;
    }

    public String getProductWarranty() {
        return ProductWarranty;
    }

    public void setProductWarranty(String productWarranty) {
        ProductWarranty = productWarranty;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getTempleColour() {
        return TempleColour;
    }

    public void setTempleColour(String templeColour) {
        TempleColour = templeColour;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public String getDiscountType() {
        return DiscountType;
    }

    public void setDiscountType(String discountType) {
        DiscountType = discountType;
    }

    public int getMRP() {
        return MRP;
    }

    public void setMRP(int MRP) {
        this.MRP = MRP;
    }

    public int getMRPAfterDiscount() {
        return MRPAfterDiscount;
    }

    public void setMRPAfterDiscount(int MRPAfterDiscount) {
        this.MRPAfterDiscount = MRPAfterDiscount;
    }

    public int getIsPower() {
        return IsPower;
    }

    public void setIsPower(int isPower) {
        IsPower = isPower;
    }

    public String getSuitsLensType() {
        return SuitsLensType;
    }

    public void setSuitsLensType(String suitsLensType) {
        SuitsLensType = suitsLensType;
    }

    public int getIsCODAvailable() {
        return isCODAvailable;
    }

    public void setIsCODAvailable(int isCODAvailable) {
        this.isCODAvailable = isCODAvailable;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getAvailableStatus() {
        return AvailableStatus;
    }

    public void setAvailableStatus(int availableStatus) {
        AvailableStatus = availableStatus;
    }

    public int getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(int isApproved) {
        IsApproved = isApproved;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeInt(ProductId);
        dest.writeString(StoreId);
        dest.writeString(StoreName);
        dest.writeString(ProductName);
        dest.writeString(Description);
        dest.writeString(ModelNo);
        dest.writeInt(ProductTypeId);
        dest.writeString(ProductType);
        dest.writeString(FrameSizeId);
        dest.writeString(FrameSize);
        dest.writeString(FrameColour);
        dest.writeString(Weight);
        dest.writeInt(WeightGroup);
        dest.writeInt(MaterialId);
        dest.writeString(Material);
        dest.writeInt(FrameMaterial);
        dest.writeInt(TempleMaterial);
        dest.writeString(PrescriptionType);
        dest.writeString(FrameStyleId);
        dest.writeString(FrameStyle);
        dest.writeInt(FrameStyleSecondary);
        dest.writeInt(FrameShapeId);
        dest.writeString(FrameShape);
        dest.writeString(FrameTypeId);
        dest.writeString(FrameType);
        dest.writeString(Collection);
        dest.writeString(ProductWarranty);
        dest.writeString(Gender);
        dest.writeString(Height);
        dest.writeString(Condition);
        dest.writeString(TempleColour);
        dest.writeInt(Price);
        dest.writeInt(Discount);
        dest.writeString(DiscountType);
        dest.writeInt(MRP);
        dest.writeInt(MRPAfterDiscount);
        dest.writeInt(IsPower);
        dest.writeString(SuitsLensType);
        dest.writeInt(isCODAvailable);
        dest.writeString(Status);
        dest.writeInt(AvailableStatus);
        dest.writeInt(IsApproved);
        dest.writeString(CreatedBy);
        dest.writeString(CreatedDate);
        dest.writeString(ImageURL);
    }
}
