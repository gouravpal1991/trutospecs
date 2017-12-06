package com.tru2specs.android.objects.responses.productlisting;

/**
 * Created by palgour on 11/17/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("StoreId")
    @Expose
    private String storeId;
    @SerializedName("StoreName")
    @Expose
    private String storeName;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ModelNo")
    @Expose
    private String modelNo;
    @SerializedName("ProductTypeId")
    @Expose
    private Integer productTypeId;
    @SerializedName("ProductType")
    @Expose
    private String productType;
    @SerializedName("FrameSizeId")
    @Expose
    private Object frameSizeId;
    @SerializedName("FrameSize")
    @Expose
    private Object frameSize;
    @SerializedName("FrameColour")
    @Expose
    private String frameColour;
    @SerializedName("Weight")
    @Expose
    private String weight;
    @SerializedName("WeightGroup")
    @Expose
    private Integer weightGroup;
    @SerializedName("MaterialId")
    @Expose
    private Integer materialId;
    @SerializedName("Material")
    @Expose
    private String material;
    @SerializedName("FrameMaterial")
    @Expose
    private Integer frameMaterial;
    @SerializedName("TempleMaterial")
    @Expose
    private Integer templeMaterial;
    @SerializedName("PrescriptionType")
    @Expose
    private String prescriptionType;
    @SerializedName("FrameStyleId")
    @Expose
    private Object frameStyleId;
    @SerializedName("FrameStyle")
    @Expose
    private Object frameStyle;
    @SerializedName("FrameStyleSecondary")
    @Expose
    private Integer frameStyleSecondary;
    @SerializedName("FrameShapeId")
    @Expose
    private Integer frameShapeId;
    @SerializedName("FrameShape")
    @Expose
    private String frameShape;
    @SerializedName("FrameTypeId")
    @Expose
    private Object frameTypeId;
    @SerializedName("FrameType")
    @Expose
    private Object frameType;
    @SerializedName("Collection")
    @Expose
    private String collection;
    @SerializedName("ProductWarranty")
    @Expose
    private String productWarranty;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Height")
    @Expose
    private String height;
    @SerializedName("Condition")
    @Expose
    private String condition;
    @SerializedName("TempleColour")
    @Expose
    private String templeColour;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("DiscountType")
    @Expose
    private Object discountType;
    @SerializedName("MRP")
    @Expose
    private Integer mRP;
    @SerializedName("MRPAfterDiscount")
    @Expose
    private Integer mRPAfterDiscount;
    @SerializedName("IsPower")
    @Expose
    private Integer isPower;
    @SerializedName("SuitsLensType")
    @Expose
    private Object suitsLensType;
    @SerializedName("isCODAvailable")
    @Expose
    private Integer isCODAvailable;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("AvailableStatus")
    @Expose
    private Integer availableStatus;
    @SerializedName("IsApproved")
    @Expose
    private Integer isApproved;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("CreatedDate")
    @Expose
    private Object createdDate;
    @SerializedName("ImageURL")
    @Expose
    private String imageURL;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Object getFrameSizeId() {
        return frameSizeId;
    }

    public void setFrameSizeId(Object frameSizeId) {
        this.frameSizeId = frameSizeId;
    }

    public Object getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(Object frameSize) {
        this.frameSize = frameSize;
    }

    public String getFrameColour() {
        return frameColour;
    }

    public void setFrameColour(String frameColour) {
        this.frameColour = frameColour;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getWeightGroup() {
        return weightGroup;
    }

    public void setWeightGroup(Integer weightGroup) {
        this.weightGroup = weightGroup;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getFrameMaterial() {
        return frameMaterial;
    }

    public void setFrameMaterial(Integer frameMaterial) {
        this.frameMaterial = frameMaterial;
    }

    public Integer getTempleMaterial() {
        return templeMaterial;
    }

    public void setTempleMaterial(Integer templeMaterial) {
        this.templeMaterial = templeMaterial;
    }

    public String getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(String prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public Object getFrameStyleId() {
        return frameStyleId;
    }

    public void setFrameStyleId(Object frameStyleId) {
        this.frameStyleId = frameStyleId;
    }

    public Object getFrameStyle() {
        return frameStyle;
    }

    public void setFrameStyle(Object frameStyle) {
        this.frameStyle = frameStyle;
    }

    public Integer getFrameStyleSecondary() {
        return frameStyleSecondary;
    }

    public void setFrameStyleSecondary(Integer frameStyleSecondary) {
        this.frameStyleSecondary = frameStyleSecondary;
    }

    public Integer getFrameShapeId() {
        return frameShapeId;
    }

    public void setFrameShapeId(Integer frameShapeId) {
        this.frameShapeId = frameShapeId;
    }

    public String getFrameShape() {
        return frameShape;
    }

    public void setFrameShape(String frameShape) {
        this.frameShape = frameShape;
    }

    public Object getFrameTypeId() {
        return frameTypeId;
    }

    public void setFrameTypeId(Object frameTypeId) {
        this.frameTypeId = frameTypeId;
    }

    public Object getFrameType() {
        return frameType;
    }

    public void setFrameType(Object frameType) {
        this.frameType = frameType;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getProductWarranty() {
        return productWarranty;
    }

    public void setProductWarranty(String productWarranty) {
        this.productWarranty = productWarranty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTempleColour() {
        return templeColour;
    }

    public void setTempleColour(String templeColour) {
        this.templeColour = templeColour;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }

    public Integer getMRP() {
        return mRP;
    }

    public void setMRP(Integer mRP) {
        this.mRP = mRP;
    }

    public Integer getMRPAfterDiscount() {
        return mRPAfterDiscount;
    }

    public void setMRPAfterDiscount(Integer mRPAfterDiscount) {
        this.mRPAfterDiscount = mRPAfterDiscount;
    }

    public Integer getIsPower() {
        return isPower;
    }

    public void setIsPower(Integer isPower) {
        this.isPower = isPower;
    }

    public Object getSuitsLensType() {
        return suitsLensType;
    }

    public void setSuitsLensType(Object suitsLensType) {
        this.suitsLensType = suitsLensType;
    }

    public Integer getIsCODAvailable() {
        return isCODAvailable;
    }

    public void setIsCODAvailable(Integer isCODAvailable) {
        this.isCODAvailable = isCODAvailable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(Integer availableStatus) {
        this.availableStatus = availableStatus;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}