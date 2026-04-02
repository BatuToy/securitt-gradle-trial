package com.btoy.trial.web.exception;

import java.util.Map;

/*
 * @created 02/04/2026 ~~ 20:06
 * author: batu
 */
public interface ParametricException  {

    Map<?, ?> getParams();

    Boolean hasAnyParam();
}
