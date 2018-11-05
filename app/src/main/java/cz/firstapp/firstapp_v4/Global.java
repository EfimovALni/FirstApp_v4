package cz.firstapp.firstapp_v4;

import cz.firstapp.firstapp_v4.model.DataResponse;
import retrofit2.Response;

/**
 * The {@code Global} need to safe (сюда созранаятся РЕСПОНЗЕ, что бы каждый раз при перевороте экрана на лазить на сервер)
 *
 * @author Alexander Efimov
 * @version 0.0.1
 * @date last changed: 14.10.2018
 */

public class Global {
    public static Response<DataResponse> response_Global = null;
}