package com.yocai.mobileAPP.views;

import com.yocai.mobileAPP.model.AddressModel;
import com.yocai.mobileAPP.model.BaseModel;

/**
 * Created by sks on 2015/9/24.
 */
public interface AddressView {
    void getAddressList(AddressModel response);
    void delete(BaseModel model);
}
