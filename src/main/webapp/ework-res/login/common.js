OAC = {
    version: '1.0.0'
};
OAC.Common =
{
    IsGuid: function (v) {
        var patrn = /^\w{8}-(\w{4}-){3}\w{12}$/;
        if (!patrn.exec(v)) return false;
        return true;
    },
    IsGuidEmpty: function (v) {
        return v == "00000000-0000-0000-0000-000000000000";
    }
    ,
    GetChineseCurrency: function (numberValue) {
        numberValue = new String(Math.round(numberValue * 100)); // 数字金额  
        var chineseValue = ""; // 转换后的汉字金额  
        var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字  
        var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位  
        var len = numberValue.length; // numberValue 的字符串长度  
        var Ch1; // 数字的汉语读法  
        var Ch2; // 数字位的汉字读法  
        var nZero = 0; // 用来计算连续的零值的个数  
        var String3; // 指定位置的数值  
        if (len > 15) {
            alert("超出计算范围");
            return "";
        }
        if (numberValue == 0) {
            chineseValue = "零元整";
            return chineseValue;
        }
        String2 = String2.substr(String2.length - len, len); // 取出对应位数的STRING2的值  
        for (var i = 0; i < len; i++) {
            String3 = parseInt(numberValue.substr(i, 1), 10); // 取出需转换的某一位的值  
            if (i != (len - 3) && i != (len - 7) && i != (len - 11) && i != (len - 15)) {
                if (String3 == 0) {
                    Ch1 = "";
                    Ch2 = "";
                    nZero = nZero + 1;
                }
                else if (String3 != 0 && nZero != 0) {
                    Ch1 = "零" + String1.substr(String3, 1);
                    Ch2 = String2.substr(i, 1);
                    nZero = 0;
                }
                else {
                    Ch1 = String1.substr(String3, 1);
                    Ch2 = String2.substr(i, 1);
                    nZero = 0;
                }
            }
            else { // 该位是万亿，亿，万，元位等关键位  
                if (String3 != 0 && nZero != 0) {
                    Ch1 = "零" + String1.substr(String3, 1);
                    Ch2 = String2.substr(i, 1);
                    nZero = 0;
                }
                else if (String3 != 0 && nZero == 0) {
                    Ch1 = String1.substr(String3, 1);
                    Ch2 = String2.substr(i, 1);
                    nZero = 0;
                }
                else if (String3 == 0 && nZero >= 3) {
                    Ch1 = "";
                    Ch2 = "";
                    nZero = nZero + 1;
                }
                else {
                    Ch1 = "";
                    Ch2 = String2.substr(i, 1);
                    nZero = nZero + 1;
                }
                if (i == (len - 11) || i == (len - 3)) { // 如果该位是亿位或元位，则必须写上  
                    Ch2 = String2.substr(i, 1);
                }
            }
            chineseValue = chineseValue + Ch1 + Ch2;
        }
        if (String3 == 0) { // 最后一位（分）为0时，加上“整”  
            chineseValue = chineseValue + "整";
        }
        return chineseValue;
    }
}

OAC.Window =
{
    CloseActivedTab: function () {
        if (parent.OACTabs) {
            parent.OACTabs.closeTabFromIfram();
        } else {
            this.CloseWindow();
        }
    },
    CloseWindow: function () {
        window.close();
    },
    OpenWindow: function (url, strTitle) {
        var width = document.body.clientWidth;
        var height = document.body.clientHeight;
        var left = (screen.width - width) / 2;
        var top = (screen.height - height) / 2;
        width -= 20;
        height -= 50;
        var params = 'width=' + width + ',height=' + height + ',top=' + top + ',left=' + left + ',toolbar=no,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,alwaysRaised=yes,z-look=yes';
        window.open(url, strTitle, params);
    },
    OpenUrlInTabWithReturnUrl: function (newUrl, retUrl) {
        if (parent.OACTabs) {
            if (!retUrl) {
                retUrl = parent.OACTabs.getActiveIframe().location.pathname;
            }
            var url = OAC.Url.SetUrlParam(newUrl, 'ReturnUrl', retUrl);
            parent.OACTabs.getActiveIframe().document.location.href = url;
        } else {
            this.OpenWindow(newUrl, retUrl);
        }
    },
    OpenUrlInNewTab: function (id, url, strTitle) {
        if (parent.OACTabs) {
            var from = parent.OACTabs.getActiveTabId();
            parent.OACTabs.addTab(id, url, strTitle, true, from, true);
        } else {
            this.OpenWindow(url, strTitle);
        }
    },
    OpenUrlInNewTabNotRefresh: function (id, url, strTitle) {
        if (parent.OACTabs) {
            //var from = parent.OACTabs.getActiveTabId();
            parent.OACTabs.addTab(id, url, strTitle, false, null, true);
        } else {
            this.OpenWindow(url, strTitle);
        }
    },
    OpenReadUrlInNewTab: function (id, url, strTitle) {
        if (url.indexOf("?") > 0) {
            url += "&formstate=Read";
        } else {
            url += "?formstate=Read";
        }
        this.OpenUrlInNewTab(id, url, strTitle);
    },
    OpenPermWindow: function (id) {
        var rnd = Date.parse(new Date());
        var url = '/Permission/SysPermMain/NewOrEdit';
        url = OAC.Url.SetUrlParam(url, "rnd", rnd);
        url = OAC.Url.SetUrlParam(url, "SourceId", id);
        popCtlPerm.SetContentUrl(url);
        popCtlPerm.Show();
    }
};

OAC.Attachments =
{
    DownloadAttribute: function (obj) {
        var iframe = $("ifrDownload");
        if (!iframe.length > 0) {
            $("body").append("<iframe style=\"display:none;\" id=\"ifrDownload\" name=\"ifrDownload\"></iframe>");
        }
    }
};

//列表页工具栏操作
OAC.ToolBar =
{
    GetToolbarItem: function (barName) {
        return $("#" + barName);
    },
    SetToolbarAllEnable: function () {
        $("#pageToolBarShowDiv .btn-toolbar button").removeAttr("disabled").removeClass("disabled");
    },
    SetToolbarAllDisable: function () {
        $("#pageToolBarShowDiv .btn-toolbar button").attr("disabled", "disabled").addClass("disabled");
    },
    SetToolbarDisable: function (barName) {
        this.GetToolbarItem(barName).attr("disabled", "disabled").addClass("disabled");
    },
    SetToolbarEnable: function (barName) {
        this.GetToolbarItem(barName).removeAttr("disabled").removeClass("disabled");
    },
    SetToolbarListDisable: function (barNameList) {
        var list = barNameList.split(',');
        for (var i = 0; i < list.length; i++) {
            this.GetToolbarItem(list[i]).attr("disabled", "disabled").addClass("disabled");
        }
    },
    SetToolbarListEnable: function (barNameList) {
        var list = barNameList.split(',');
        for (var i = 0; i < list.length; i++) {
            this.GetToolbarItem(list[i]).removeAttr("disabled").removeClass("disabled");
        }
    }
};

OAC.Url =
{
    GetQueryString: function (name) {
        var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
        if (reg.test(location.href))
            return decodeURIComponent(RegExp.$2.replace(/\+/g, " "));
        return "";
    },
    GetRootPath: function () {
        return window.location.protocol + '//' + window.location.host + '/';
    },
    SetUrlParam: function (url, param, v) {
        var re = new RegExp("(\\\?|&)" + param + "=([^&]+)(&|$)", "i");
        var m = url.match(re);
        if (m) {
            return (url.replace(re, function ($0, $1, $2) { return ($0.replace($2, v)); }));
        } else {
            if (url.indexOf('?') == -1)
                return (url + '?' + param + '=' + v);
            else
                return (url + '&' + param + '=' + v);
        }
    }
};

OAC.SelectUser =
{
    initUserSelect: function (id) {
        $("div[id^='userDiv_" + id + "'] div.selectUserEdit").each(function (index, domEle) {
            var divid = $(this).data("userdiv");
            var mulit = $(this).data("mulit");
            initUserSelectModel(divid, mulit);
        });
    }
}

OAC.GridView =
{
    BatchEditRowValidatingRequired: function (s, e, columnName, errMsg) {

        var curColumn = s.GetColumnByField(columnName);
        var cellValidationInfo = e.validationInfo[curColumn.index];
        if (!cellValidationInfo) return;
        var value = cellValidationInfo.value;
        if (!ASPxClientUtils.IsExists(value) || ASPxClientUtils.Trim(value).toString() === "") {
            cellValidationInfo.isValid = false;
            cellValidationInfo.errorText = errMsg;
        }
    },
    BatchEditRowValidatingCompareMin: function (s, e, columnName, minValue, errMsg) {

        var curColumn = s.GetColumnByField(columnName);
        var cellValidationInfo = e.validationInfo[curColumn.index];
        if (!cellValidationInfo) return;
        var value = cellValidationInfo.value;
        if (ASPxClientUtils.IsExists(value) && ASPxClientUtils.Trim(value).toString() != "" && parseFloat(value) < parseFloat(minValue)) {
            cellValidationInfo.isValid = false;
            cellValidationInfo.errorText = errMsg;
        }
    },
    BatchEditRowValidatingCompareMax: function (s, e, columnName, maxValue, errMsg) {

        var curColumn = s.GetColumnByField(columnName);
        var cellValidationInfo = e.validationInfo[curColumn.index];
        if (!cellValidationInfo) return;
        var value = cellValidationInfo.value;
        if (ASPxClientUtils.IsExists(value) && ASPxClientUtils.Trim(value).toString() != "" && parseFloat(value) > parseFloat(maxValue)) {
            cellValidationInfo.isValid = false;
            cellValidationInfo.errorText = errMsg;
        }
    },
    BatchEditRowValidatingCompare: function (s, e, columnName, minValue, maxValue, errMsg) {

        var curColumn = s.GetColumnByField(columnName);
        var cellValidationInfo = e.validationInfo[curColumn.index];
        if (!cellValidationInfo) return;
        var value = cellValidationInfo.value;
        if (ASPxClientUtils.IsExists(value) && ASPxClientUtils.Trim(value).toString() != "" && (parseFloat(value) > parseFloat(maxValue) || (parseFloat(value) < parseFloat(maxValue)))) {
            cellValidationInfo.isValid = false;
            cellValidationInfo.errorText = errMsg;
        }
    },
    BatchEditComputeSumValue: function (s, e, columnName, fixedNum, prefix) {
        if (!prefix) {
            prefix = "labSum_";
        }
        var originalValue = s.batchEditApi.GetCellValue(e.visibleIndex, columnName);
        var newValue = e.rowValues[(s.GetColumnByField(columnName).index)].value;
        var dif = newValue - originalValue;
        var lab = aspxGetControlCollection().elements[prefix + columnName];
        if (fixedNum == null) {
            fixedNum = 2;
        }
        lab.SetValue((parseFloat(lab.GetValue().replace(/,/g, '') - 0) + dif).toFixed(fixedNum));
    },
    DeletedRowBatchEditComputeSumValue: function (s, e, columnName, fixedNum, prefix) {
        if (!prefix) {
            prefix = "labSum_";
        }
        var dif = s.batchEditApi.GetCellValue(e.visibleIndex, columnName);
        var lab = aspxGetControlCollection().elements[prefix + columnName];
        if (fixedNum == null) {
            fixedNum = 2;
        }
        lab.SetValue((parseFloat(lab.GetValue().replace(/,/g, '') - 0) - dif).toFixed(fixedNum));
    },
    GetBatchEditSumValue: function (columnName, fixedNum, prefix) {
        if (!prefix) {
            prefix = "labSum_";
        }
        if (fixedNum == null) {
            fixedNum = 2;
        }
        var lab = aspxGetControlCollection().elements[prefix + columnName];
        return parseFloat(lab.GetValue().replace(/,/g, '') - 0).toFixed(fixedNum);
    },

    BatchEditRowTextBoxStartEditing: function (s, e, controlId, columnField) {
        var curColumn = s.GetColumnByField(columnField);
        if (!e.rowValues.hasOwnProperty(curColumn.index))
            return;
        var cellInfo = e.rowValues[curColumn.index];
        var lb = aspxGetControlCollection().Get(controlId);
        if (lb != null) {
            lb.SetValue(cellInfo.text);
        }
    },
    SelectUserBatchEditStartEditing: function (s, e, controlId, columnField) {
        var curColumn = s.GetColumnByField(columnField);
        if (!e.rowValues.hasOwnProperty(curColumn.index))
            return;
        var cellInfo = e.rowValues[curColumn.index];
        var userControl = $("div[id^='userDiv_" + controlId + "'] div.selectUserEdit");
        if (userControl.length > 0) {
            var divid = $(userControl[0]).data("userdiv");
            var mulit = $(userControl[0]).data("mulit");
            if (cellInfo.value) {
                var valueAry = cellInfo.value.split(';');
                var textAry = cellInfo.text.split(';');
                var myReg = /(.+)\((.+)\)/;
                var userAry = new Array();
                for (var i = 0; i < valueAry.length; i++) {
                    if (valueAry[i] === '') continue;
                    var r = textAry[i].match(myReg);
                    var userInfo = { "id": valueAry[i], "name": r[1], "code": r[2] }
                    userAry.push(userInfo);
                }
                _requestSelectedUser(userAry, divid, mulit, '');

            } else {
                _requestSelectedUser(null, divid, mulit, '');
            }
        }
    },
    SelectUserBatchEditEndEditing: function (s, e, controlId, columnField) {
        var curColumn = s.GetColumnByField(columnField);
        if (!e.rowValues.hasOwnProperty(curColumn.index))
            return;
        var cellInfo = e.rowValues[curColumn.index];
        var userControl = $("div[id^='userDiv_" + controlId + "'] div.selectUserEdit");
        if (userControl.length > 0) {
            var divid = $(userControl[0]).data("userdiv");
            var result = getUserArrByDivId(divid);
            if (result.length > 0) {
                var textAry = new Array();
                var valueAry = new Array();
                for (var idex in result) {
                    valueAry.push(result[idex].id);
                    textAry.push(result[idex].name + "(" + result[idex].code + ")");
                }
                cellInfo.value = valueAry.join(';');
                cellInfo.text = textAry.join(';');
            } else {
                cellInfo.value = "";
                cellInfo.text = "";
            }
        }
    },
    FileUploadBatchEditStartEditing: function (s, e, controlId, columnField) {
        var curColumn = s.GetColumnByField(columnField);
        if (!e.rowValues.hasOwnProperty(curColumn.index))
            return;
        var cellInfo = e.rowValues[curColumn.index];
        var uploadDiv = $("div[id^='" + controlId + "_']");
        if (uploadDiv.length > 0) {
            var that = $(uploadDiv[0]);
            var divid = that.attr("id");
            var multi = that.data("multi");
            var fileTypeExts = that.data("filetypeexts");
            var editMode = that.data("editmode");
            var uploadedFileStr = $(cellInfo.text).find("ul.qq-upload-list").html();
            createUploader(divid, editMode, uploadedFileStr, multi, fileTypeExts);
        }
    },
    FileUploadBatchEditEndEditing: function (s, e, controlId, columnField) {
        var curColumn = s.GetColumnByField(columnField);
        if (!e.rowValues.hasOwnProperty(curColumn.index))
            return;
        var cellInfo = e.rowValues[curColumn.index];
        var uploadDiv = $("div[id^='" + controlId + "_']");
        if (uploadDiv.length > 0) {
            var divid = $(uploadDiv[0]).attr("id");
            var result = getUploadedFileByDivId(divid);
            cellInfo.value = result;
            cellInfo.text = '<div class="qq-uploader "><ul class="qq-upload-list" style="margin-top: 10px; text-align: left;">' + uploadDiv.find("ul.qq-upload-list").html() + '</ul></div>';
        }
    },
    ImgUploadBatchEditStartEditing: function (s, e, controlId, columnField) {
        var curColumn = s.GetColumnByField(columnField);
        if (!e.rowValues.hasOwnProperty(curColumn.index))
            return;
        var uploads = $("div[id^='" + controlId + "_']");

        if (uploads.length > 0) {
            var divid = "";
            for (var i = 0; i < uploads.length; i++) {
                var id = $(uploads[i]).attr("id");
                var carouselreg = new RegExp("carousel$");
                if (carouselreg.test(id)) {
                    divid = id;
                }
            }

            var modelid = divid.replace("-carousel", "");
            createImgUploader(modelid, divid, true);
            $(divid + " .unUpload").click(function () {
                $("#" + modelid + " input[type='file']").trigger("click");
            });
        }


    },
    ImgUploadBatchEditEndEditing: function (s, e, controlId, columnField) {
        //var curColumn = s.GetColumnByField(columnField);
        //if (!e.rowValues.hasOwnProperty(curColumn.index))
        //    return;
        //var cellInfo = e.rowValues[curColumn.index];
        //var uploads = $("div[id^='" + controlId + "_']");
        //if (uploads.length > 0) {
        //    var iptid = "";
        //    var divid = "";
        //    for (var i = 0; i < uploads.length; i++) {
        //        var id = $(uploads[i]).attr("id");
        //        var carouselreg = new RegExp("carousel$");
        //        var hiddenreg = new RegExp("hidden$");
        //        if (carouselreg.test(id)) {
        //            divid = id;
        //        }
        //        if (hiddenreg.test(id)) {
        //            iptid = id;
        //        }
        //    }

        //    cellInfo.value = $("#" + iptid).val();
        //    cellInfo.text = $("#" + divid).find(".save,.remove").remove().end().prop("outerHTML");
        //}
    },
    GridDialogSingleChooseBatchEditStartEditing: function (s, e, controlId, columnField) {
        var curColumn = s.GetColumnByField(columnField);
        if (!e.rowValues.hasOwnProperty(curColumn.index))
            return;
        var cellInfo = e.rowValues[curColumn.index];
        $("#" + controlId).val(cellInfo.value);
        $("input[name='" + controlId + "']").val(cellInfo.text);
    },
    GridDialogSingleChooseBatchEditEndEditing: function (s, e, controlId, columnField) {
        var curColumn = s.GetColumnByField(columnField);
        if (!e.rowValues.hasOwnProperty(curColumn.index))
            return;
        var cellInfo = e.rowValues[curColumn.index];
        var value = $("#" + controlId).val();
        var text = $("input[name='" + controlId + "']").val();
        if (value) {
            cellInfo.value = value;
            cellInfo.text = text;
        } else {
            cellInfo.value = null;
            cellInfo.text = null;
        }
    }

}

//上传文件在弹出的表单中使用时需要进行的初始化
OAC.FileUploador = {
    initFileUpload: function (id) {
        var s = $("div[id^='" + id + "_']");
        $("div[id^='" + id + "']").each(function () {
            var that = $(this);
            var divid = that.attr("id");
            var multi = that.data("multi");
            var fileTypeExts = that.data("filetypeexts");
            var editMode = that.data("editmode");
            var uploadedFileStr = $(that.find(".fileUploaderedHidDiv")[0]).html();
            createUploader(divid, editMode, uploadedFileStr, multi, fileTypeExts);
        });
    }
}


OAC.TreeView = {
    SetChildrenCheckedEvent: function (s, e) {
        var isChecked = e.node.GetChecked();
        OAC.TreeView.SetChildrenChecked(e.node, isChecked);
    },
    SetChildrenChecked: function (node, isChecked) {
        var childrenCount = node.GetNodeCount();
        for (var i = 0; i < childrenCount; i++) {
            var curNode = node.GetNode(i);
            curNode.SetChecked(isChecked);
            OAC.TreeView.SetChildrenChecked(curNode, isChecked);
        }
    },
    GetAllCheckedNodes: function (tree) {
        var arrCheckedNode = new Array();
        var childrenCount = tree.GetNodeCount();
        for (var i = 0; i < childrenCount; i++) {
            OAC.TreeView.GetCheckedNodes(tree.GetNode(i), arrCheckedNode);
        }
        return arrCheckedNode;
    },
    GetCheckedNodes: function (node, arrCheckedNode) {
        var isChecked = node.GetChecked();
        if (isChecked) {
            arrCheckedNode.push(node);
        }
        var childrenCount = node.GetNodeCount();
        for (var i = 0; i < childrenCount; i++) {
            var curNode = node.GetNode(i);
            OAC.TreeView.GetCheckedNodes(curNode, arrCheckedNode);
        }
    },
    GetCheckedNodesValue: function (tree) {
        var nodes = OAC.TreeView.GetAllCheckedNodes(tree);
        var valueAry = new Array();
        for (var idex in nodes) {
            valueAry.push(nodes[idex].name);
        }
        return valueAry.join(';');
    }
    ,
    GetCheckedNodesText: function (tree) {
        var nodes = OAC.TreeView.GetAllCheckedNodes(tree);
        var valueAry = new Array();
        for (var idex in nodes) {
            valueAry.push($(nodes[idex].GetHtmlElement()).last("span")[0].innerText);
        }
        return valueAry.join(';');
    }
}

OAC.Form = {
    SetDevExpressDropDownReadOnly: function (controlName) {
        var lb = aspxGetControlCollection().Get(controlName);
        if (lb != null) {
            lb.readOnly = true;
        }
        $("input[name='" + controlName + "']").unbind().attr("readonly", "readonly").removeAttr("onclick onfocus onblur onkeyup onkeydown onmousedown").click(function () { return false; });
        $("input[name='" + controlName + "']").parents("table").find(".dxeButtonEditButton_MetropolisBlue").hide();
        $("input[name='" + controlName + "']").parents("table").find("td.dxic").removeAttr("onmousedown");

    },
    SetDevExpressHtmlEditorReadOnly: function (controlName) {
        var lb = aspxGetControlCollection().Get(controlName);
        if (lb != null) {
            lb.allowDesignView = false;
            lb.SetActiveTabByName("Preview");
            //aspxTCTClick(event, controlName + '_TC', 2);
            $("#" + controlName + " .dxheStatusBar_MetropolisBlue").hide();
        }
    },
    SetSelectUserReadOnly: function (scopeName, controlName) {
        var div = $(scopeName + "input[name='" + controlName + "']").parent();
        div.find("[data-toggle='modal']").hide();
        //div.find(".selectUserEdit").css("border-radius", "4px 4px 4px 4px");
    },
    SetImgUploadReadOnly: function (scopeName, controlName) {
        var div = $(scopeName + "input[name='" + controlName + "']").parent();
        div.find("div.slide div.uploadedImage div.remove").remove();
        div.find("div.slide div.unUpload").parent().remove();
    },
    SetFileUploadReadOnly: function (scopeName, controlName) {
        var div = $(scopeName + "input[name='" + controlName + "']").parent();
        div.find("div.qq-uploader div.qq-upload-button").remove();
        div.find("div.qq-uploader ul.qq-upload-list span.deletefile").remove();
    },
    SetDepartmentIdReadOnly: function () {
        var modal = $("li.department div.btn-group a[data-toggle='modal']");
        var departmenttext = modal.first().text();
        modal.hide();
        modal.parent().append(departmenttext).removeClass("btn-group");
    },
    SetGridDialogReadOnly: function (scopeName, controlName) {
        var div = $(scopeName + "input[name='" + controlName + "']").parent();
        div.find("[data-toggle='modal']").hide();
        div.removeClass("input-append").addClass("divInline");
    },
    SetDepartmentIdHide: function () {
        $("li.department").hide();
    },
    SetFormReadOnly: function () {
        try {
            var devControls = aspxGetControlCollection().elements;
            for (var con in devControls) {
                var lb = aspxGetControlCollection().Get(con);
                if (lb != null) {
                    lb.SetEnabled(false);
                    $(lb.mainElement).css("background-color", "#eee");
                    $(lb.mainElement).find("input").css("background-color", "#eee");
                    if (lb.DropDown) {  //带下拉按钮的控件
                        this.SetDevExpressDropDownReadOnly(con);
                    }
                    if (lb.allowBatchEditing) {
                        lb.allowBatchEditing = false;
                    }
                    //if (lb.SetActiveTabByName) {  //富文本控件
                    //    this.SetDevExpressHtmlEditorReadOnly(con);
                    //}

                }
            }
        } catch (e) {

        }


        $("input").unbind().attr("readonly", "readonly").removeAttr("onclick onfocus onblur onkeyup onkeydown").click(function () { return false; });
        $("textarea").unbind().attr("readonly", "readonly");
        $("select").unbind().attr("readonly", "readonly");
        //处理必填验证的星号
        $(".field-validation-error").remove();
        $(".required").removeClass("required");
        //处理表单中的信息归属
        var departmenttext = $("li.department div.btn-group a[data-toggle='modal']").first().text();
        $("li.department div.btn-group").append(departmenttext).removeClass("btn-group");
        //处理弹窗
        $("[data-toggle='modal']").hide().parent().removeClass("input-append").addClass("divInline");
        //处理从表表头功能按钮
        $(".dxgvTitlePanel_MetropolisBlue").hide();
        //处理人员选择控件样式
        $(".selectUserEdit").find(".unborderedit").remove();
        //处理图片上传控件
        $("div.slide div.uploadedImage div.remove").remove();
        $("div.slide div.unUpload").parent().remove();
        //文件上传控件
        $("div.qq-uploader div.qq-upload-button").remove();
        $("div.qq-uploader ul.qq-upload-list span.deletefile").remove();
        //去掉条码控件按钮
        //$("a.tdCodeBuilt").remove();
        //去掉保存按钮
        $("div.f-form-toolbar .btn:not([id='btnExit']):not([readonlynohide='true'])").remove();

        //Spin控件
        $(".dxeSpinIncButton_MetropolisBlue").parent().hide();
    },
    SetFormReadOnlyWithTable: function () {
        try {
            var devControls = aspxGetControlCollection().elements;
            for (var con in devControls) {
                var lb = aspxGetControlCollection().Get(con);
                if (lb != null) {
                    lb.SetEnabled(false);
                    //$(lb.mainElement).css("background-color", "#eee");
                    //$(lb.mainElement).find("input").css("background-color", "#eee");
                    if (lb.DropDown) {  //带下拉按钮的控件
                        this.SetDevExpressDropDownReadOnly(con);
                    }
                    if (lb.allowBatchEditing) {
                        lb.allowBatchEditing = false;
                    }
                    //if (lb.SetActiveTabByName) {  //富文本控件
                    //    this.SetDevExpressHtmlEditorReadOnly(con);
                    //}

                }
            }
        } catch (e) {

        }


        $("input").unbind().attr("readonly", "readonly").removeAttr("onclick onfocus onblur onkeyup onkeydown").click(function () { return false; });
        $("textarea").unbind().attr("readonly", "readonly");
        $("select").unbind().attr("readonly", "readonly");
        //处理必填验证的星号
        $(".field-validation-error").remove();
        $(".required").removeClass("required");
        //处理表单中的信息归属
        var departmenttext = $("li.department div.btn-group a[data-toggle='modal']").first().text();
        $("li.department div.btn-group").append(departmenttext).removeClass("btn-group");
        //处理弹窗
        $("[data-toggle='modal']").hide().parent().removeClass("input-append").addClass("divInline");
        //处理从表表头功能按钮
        $(".dxgvTitlePanel_MetropolisBlue").hide();
        //处理人员选择控件样式
        $(".selectUserEdit").find(".unborderedit").remove();
        //处理图片上传控件
        $("div.slide div.uploadedImage div.remove").remove();
        $("div.slide div.unUpload").parent().remove();
        //文件上传控件
        $("div.qq-uploader div.qq-upload-button").remove();
        $("div.qq-uploader ul.qq-upload-list span.deletefile").remove();
        //去掉条码控件按钮
        //$("a.tdCodeBuilt").remove();
        //去掉保存按钮
        $("div.f-form-toolbar .btn:not([id='btnExit']):not([readonlynohide='true'])").remove();

        //Spin控件
        $(".dxeSpinIncButton_MetropolisBlue").parent().hide();
    },
    SetControlReadOnly: function (scopeName, controlName) {
        if (controlName == "DepartmentId") {
            this.SetDepartmentIdReadOnly();
            return;
        }

        var pName = "";
        if (scopeName && scopeName != '') {
            pName = "#" + scopeName + " ";
        }
        var lb = aspxGetControlCollection().Get(controlName);
        if (lb != null) {
            $(lb.mainElement).css("background-color", "#eee");
            $(lb.mainElement).find("input").css("background-color", "#eee");
            if (lb.DropDown) {
                this.SetDevExpressDropDownReadOnly(controlName);
            } else {
                if (lb.SetActiveTabByName) {
                    this.SetDevExpressHtmlEditorReadOnly(controlName);
                }
                if (lb.allowBatchEditing) {
                    lb.allowBatchEditing = false;
                }
                lb.readOnly = true;
                lb.InvokeClick = function () { };
                lb.OnItemClick = function (index) { };
                lb.OnFocus = function () { };
            }
            return;
        }
        //判断是否是隐藏控件
        var isHide = $(pName + ":input:hidden[name='" + controlName + "']").length > 0;
        if (isHide) {
            this.SetSelectUserReadOnly(pName, controlName);
            this.SetImgUploadReadOnly(pName, controlName);
            this.SetFileUploadReadOnly(pName, controlName);
            this.SetGridDialogReadOnly(pName, controlName);

        } else {
            $(pName + ":input[name='" + controlName + "']").unbind().attr("readonly", "readonly").removeAttr("onclick onfocus onblur onkeyup onkeydown").click(function () { return false; }).parents("div.control-group").find(".field-validation-error").remove();
        }
    },
    SetControlHide: function (scopeName, controlName) {
        if (controlName == "DepartmentId") {
            this.SetDepartmentIdHide();
            return;
        }
        var pName = "";
        if (scopeName && scopeName != '') {
            pName = "#" + scopeName + " ";
        }

        var curDiv = $(pName + ":input[name='" + controlName + "']").parents("div.control-group");
        curDiv.hide();
        curDiv.parents("div.row-fluid").find("div.control-group").css("margin-left", "0");

        $(pName + "." + controlName).hide();
        $(pName + "#" + controlName).hide();
    },
    ProcessTaskControlData: function (data) {
        if (data && data != "") {
            //处理JSon数据
            var controlData = eval(data);
            var scopeId, fieldName, canReader, canWrite;
            if (controlData.length > 0) {
                for (var i = 0; i < controlData.length; i++) {
                    var curData = controlData[i];
                    scopeId = curData.ScopeId;
                    var wfControlFieldInfos = curData.WfControlFieldInfos;
                    for (var j = 0; j < wfControlFieldInfos.length; j++) {
                        fieldName = wfControlFieldInfos[j].FieldName;
                        canReader = wfControlFieldInfos[j].CanReader;
                        canWrite = wfControlFieldInfos[j].CanWrite;
                        if (fieldName != '' && !canWrite && canReader) {
                            //控制只读
                            this.SetControlReadOnly(scopeId, fieldName);
                        } else if (fieldName != '' && !canWrite && !canReader) {
                            //隐藏控件
                            this.SetControlHide(scopeId, fieldName);
                        }
                    }
                }
            }
        }
    }
};

function SetIFrameSrc(iFramId, srcUrl) {
    $("#" + iFramId)[0].src = srcUrl;
}

/**
*遮罩
*/
var LoadingUtils = {
    Open: function () {
        //var top = 0;// $(this).offset() == undefined ? 0 : $(this).offset().top;
        //var left = 0;//$(this).offset() == undefined ? 0 : $(this).offset().left;

        //var appender = null;
        //if ($(this).parent().length == 0) {
        //    appender = "body";
        //} else {
        //    appender = $(this);
        //}
        //$("<div class=\"mask\"></div>").css({
        //    display: "block",
        //    width: $(this).outerWidth(),//100%  
        //    height: $(this).outerHeight(),//height  
        //    top: top,
        //    left: left
        //}).appendTo(appender); //body  
        //$("<div class=\"mask-msg\"></div>").html("正在处理，请稍候...")
        //    .appendTo(appender).css({
        //        display: "block",
        //        left: ($(this).outerWidth() - 153) / 2 + left,
        //        top: ($(this).outerHeight() - 42) / 2 + top
        //    });
    },
    Close: function () {
        //$(this).find(".mask").remove();
        //$(this).find(".mask-msg").remove();
    }
};

//常用弹窗控件中的返回Id转换为显示列
function DisplayIdToNameForControl(targetControlId, dataSource, columnName, returnValue, hiddenControlId, getAllValue, endCallBack, selectedValues) {
    $.ajax({
        url: "/api/ExtentionApi/GetDialogAllValue",
        data: { dataSource: dataSource, columnName: columnName, idStr: returnValue },
        success: function (data) {
            var dataObj = eval("(" + data + ")");
            var displayText = '';
            for (var i = 0; i < dataObj.ds.length; i++) {
                displayText += eval("dataObj.ds[i]." + columnName);
            }
            var targetControl = document.getElementById(targetControlId);
            if (targetControl.nodeName == "SPAN") {
                document.getElementById(targetControlId).innerText = displayText;
            } else {
                document.getElementById(targetControlId).value = displayText;
            }

            document.getElementById(hiddenControlId).value = returnValue;
            if (getAllValue) {
                $('#' + hiddenControlId).attr('AllValue', data);
            }
            if (endCallBack && endCallBack != '') {
                eval(endCallBack + "('" + returnValue + "','" + selectedValues + "')");
            }
            $("form").valid();
        }
    });
}

function BuildArticleCatalogTable(targetControlId, dataSource, columnName, returnValue, hiddenControlId) {
    UpdateArticleCatalogTable(targetControlId, returnValue, hiddenControlId);
}

function BuildArchiveCatalogTable(targetControlId, dataSource, columnName, returnValue, hiddenControlId) {
    UpdateArchiveCatalogTable(targetControlId, returnValue, hiddenControlId);
}

//弹窗控件中的返回Id转换为拼接的表格字符串，用于文章的栏目选择
function UpdateArticleCatalogTable(targetControlId, returnValue, hiddenControlId) {
    $.ajax({
        url: "/api/ExtentionApi/GetArticleCatalogTableHtml",
        data: { idStr: returnValue },
        success: function (data) {
            var htmlStr = '<ul>';
            var catalogStrList = data.split(';');
            for (var i = 0; i < catalogStrList.length; i++) {
                var trHtmlStr = '<li>' + catalogStrList[i] + '</li>';
                htmlStr += trHtmlStr;
            }
            $('#' + targetControlId).html(htmlStr + '</ul>');
            document.getElementById(hiddenControlId).value = returnValue;

        }
    });
}

//弹窗控件中的返回Id转换为拼接的表格字符串，用于资料归档目录选择
function UpdateArchiveCatalogTable(targetControlId, returnValue, hiddenControlId) {
    $.ajax({
        url: "/api/ExtentionApi/GetArchiveCatalogTableHtml",
        data: { idStr: returnValue },
        success: function (data) {
            var htmlStr = '<ul>';
            var catalogStrList = data.split(';');
            for (var i = 0; i < catalogStrList.length; i++) {
                var trHtmlStr = '<li>' + catalogStrList[i] + '</li>';
                htmlStr += trHtmlStr;
            }
            $('#' + targetControlId).html(htmlStr + '</ul>');
            document.getElementById(hiddenControlId).value = returnValue;
            $("form").valid();
        }
    });
}


function newGuidParse(guidStr) {
    var guid = "";
    if (guidStr.length == 32) {
        for (var i = 1; i <= 32; i++) {
            guid += guidStr.substr(i - 1, 1);
            if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
                guid += "-";
        }
    } else {
        guid = guidStr;
    }
    return guid;
}

//暂时去除快速导航
//jQuery(function () {
//    var form = $(".f-form-body");

//    if (form.length <= 0)
//        return;
//    if ($(form[0]).attr("quicknavigation") && $(form[0]).attr("quicknavigation") != false)
//        return;

//    var navLiHtml = "";
//    var legends = $("fieldset legend");


//    for (var i = 0; i < legends.length; i++) {
//        var id = "legends_" + i;
//        $(legends[i]).attr("id", id);
//        navLiHtml += "<li><a navlist href='#" + id + "'>" + $(legends[i]).get(0).firstChild.nodeValue + "</a></li>";
//    }

//    var navHtml = '<div class="f-form-section-nav" ';

//    if ($(document).outerWidth() < 1000) {
//        navHtml += "style='display:none' ";
//    }

//    navHtml += '><div class="f-form-section-container">' +
//        '<div class="well">' +
//        '<ul class="nav nav-list">' +
//        '<li class="nav-header">快速跳转</li>' +
//        '<li class="divider"></li>';

//    if (legends.length > 0) {
//        navHtml += navLiHtml + '<li class="divider"></li>';
//    }

//    navHtml += '<li class="top">' +
//     '<a href="#">返回顶部</a>' +
//     '<i class="icon-chevron-up"> </i>' +
//     '</li>' +
//     '</ul>' +
//     '</div>' +
//     '</div>' +
//     '</div>';
//    $(".f-form-body").after(navHtml);

//    window.setTimeout(function () {
//        $(".f-form-section-nav li a[navlist]").each(function () {
//            var hr = $($(this).attr("href"));
//            var offset = hr.offset();
//            var anh = offset.top;
//            hr.attr("offsettop", anh);
//        });

//        $(".f-form-section-nav li a[navlist]").click(function () {
//            var hr = $($(this).attr("href"));
//            var anh = hr.attr("offsettop");
//            $("html,body").stop().animate({ scrollTop: anh }, 600);
//        });
//    }, 80);

//    try {
//        window._resizetime = null;
//    } catch (e) {

//    }

//    $(window).resize(function () {
//        if (window._resizetime) {
//            clearTimeout(window._resizetime);
//        }
//        window._resizetime = setTimeout(function () {
//            if ($(document).outerWidth() < 1000) {
//                $(".f-form-section-nav").hide();
//            } else {
//                $(".f-form-section-nav").show();
//            }
//        }, 80);
//    });

//});

//调整Dev控件高度
function AdjustControlSize(control) {
    var pHeight = $("#" + control.uniqueID).parent().height();
    control.SetHeight(pHeight);
    control.AdjustControl();
}

function OpenDetails(url, destTab, destTabName) {
    parent.OACTabs.closeTabFormeOut(destTab);
    parent.OACTabs.addTab(destTab, url, destTabName);
}

//网格新增行
function GridView_AddNewRow(gridName) {
    aspxGetControlCollection().elements[gridName].AddNewRow();
}

//网格编辑行
function GridView_StartEditFocusedRow(gridName) {
    var grid = aspxGetControlCollection().elements[gridName];
    var idex = grid.GetFocusedRowIndex();
    if (idex >= 0) {
        grid.StartEditRow(idex);
    }
}

//网格编辑行
function GridView_DeleteRow(gridName) {
    var grid = aspxGetControlCollection().elements[gridName];
    var idex = grid.GetFocusedRowIndex();
    if (idex >= 0) {
        if (confirm("确定要删除？")) {
            grid.DeleteRow(idex);
        }
    }
}


//二维码生成控件   开始

function ReBuildMG(imgId, modelId, xDataSource, displayColumns, kKSColumn, title, columnName, attachPathCtlId, saveField) {
    $.ajax({
        url: OAC.Url.GetRootPath() + 'RebuildTDCode/Index',
        type: "Get",
        data: {
            modelId: modelId, xDataSource: xDataSource, displayColumns: displayColumns, kKSColumn: kKSColumn, title: title, saveField: saveField
        },
        success: function (response) {
            var attachPath = $('#' + attachPathCtlId).val();
            var srcUrl = attachPath + response;
            $('#' + columnName).val(response);
            $("#" + imgId).attr("src", srcUrl);
        }
    });
}
//二维码生成控件   结束



//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果
function accDiv(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length;
    } catch (e) { }
    try {
        t2 = arg2.toString().split(".")[1].length;
    } catch (e) { }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return (r1 / r2) * pow(10, t2 - t1);
    }
}
//给Number类型增加一个div方法，调用起来更加方便。
Number.prototype.div = function (arg) {
    return accDiv(this, arg);
};
//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以arg2的精确结果
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    } catch (e) { }
    try {
        m += s2.split(".")[1].length;
    } catch (e) { }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}
//给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};
//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length;
    } catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    } catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    return (arg1 * m + arg2 * m) / m;
}
//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg) {
    return accAdd(arg, this);
}
//减法函数
function accSub(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    } catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    } catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    //last modify by deeka
    //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg2 * m - arg1 * m) / m).toFixed(n);
}
///给number类增加一个sub方法，调用起来更加方便
Number.prototype.sub = function (arg) {
    return accSub(arg, this);
}

window.alert = function (txt) {
    asyncbox.alert(txt, "提示");
}

function CPos(left, top) {
    this.left = left;
    this.top = top;
}

function GetObjPos(aTarget) {
    var target = aTarget;
    var pos;
    var cur = aspxGetControlCollection().Get(target.name);
    if (cur) {
        var curElement = cur.GetMainElement();
        pos = new CPos(curElement.offsetLeft, curElement.offsetTop);
        target = curElement.offsetParent;
        while (target) {
            pos.left += target.offsetLeft;
            pos.top += target.offsetTop;
            target = target.offsetParent;
        }
        return pos;
    } else {
        pos = new CPos(target.offsetLeft, target.offsetTop);
        target = target.offsetParent;
        while (target) {
            pos.left += target.offsetLeft;
            pos.top += target.offsetTop;
            target = target.offsetParent;
        }
        return pos;
    }

}

