@echo off
setlocal enabledelayedexpansion

rem Loop from 1 to 100
for /L %%i in (1,1,100) do (
    set /a randomQuantity=!random! %% 3 + 1
    set /a randomPrice=!random! %% 1000 + 100
    set /a productNumber=!random! %% 5 + 1

    rem Use curl to send POST request
    curl -X POST http://localhost:8080/api/orders ^
    -H "Content-Type: application/json" ^
    -d "{\"customerName\":\"Customer%%i\",\"product\":\"Product!productNumber!\",\"quantity\":!randomQuantity!,\"price\":!randomPrice!," ^
    "\"status\":\"PLACED\"}"

    echo Order %%i created
)

endlocal
