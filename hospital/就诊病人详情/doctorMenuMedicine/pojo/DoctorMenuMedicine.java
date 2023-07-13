package com.qhit.doctorMenuMedicine.pojo;


import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.medicineCode.pojo.MedicineCode;

/**
* Created by GeneratorCode on 2018/12/25
*/

public class DoctorMenuMedicine {

    private Integer mdId;    //主键
    private Integer menuId;    //套餐
    private Integer codeId;    //药品
    private Integer amt;    //数量

    private String medicineName;

    private MedicineCode medicineCode;
    private DoctorMenu doctorMenu;

    public DoctorMenu getDoctorMenu() {
        return doctorMenu;
    }

    public void setDoctorMenu(DoctorMenu doctorMenu) {
        this.doctorMenu = doctorMenu;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public MedicineCode getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(MedicineCode medicineCode) {
        this.medicineCode = medicineCode;
    }

    public Integer getMdId() {
        return mdId;
    }

    public void setMdId(Integer mdId) {
        this.mdId = mdId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }
}
