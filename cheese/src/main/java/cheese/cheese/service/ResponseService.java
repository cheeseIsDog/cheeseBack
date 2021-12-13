package cheese.cheese.service;

import cheese.cheese.ResultForm.CommonResult;
import cheese.cheese.ResultForm.ListResult;
import cheese.cheese.ResultForm.SingleResult;
import cheese.cheese.dto.Enum.CommonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        this.setSuccessResult(result);
        return result;
    }

    public <T> SingleResult<T> getBooleanResult(Boolean data) {
        SingleResult<T> result = new SingleResult<>();
        if(data) {
            this.setFailResult(result);
        } else {
            this.setSuccessResult(result);
        }
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> data) {
        ListResult<T> result = new ListResult<>();
        result.setList(data);
        this.setSuccessResult(result);
        return result;
    }

    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }

    public CommonResult getFailResult(CommonResponse commonResponse) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(commonResponse.getCode());
        result.setMsg(commonResponse.getMsg());
        return result;
    }

    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(CommonResult result) {
        result.setSuccess(false);
        result.setCode((CommonResponse.FAIL.getCode()));
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
