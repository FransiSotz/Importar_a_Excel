USE [TDRGT]
GO
/****** Object:  StoredProcedure [dbo].[SP_TRAFICO]    Script Date: 29/02/2024 09:25:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_TRAFICO] 
@PARAMETRO VARCHAR(MAX)
AS
BEGIN
DECLARE @INDEX INT,
		@NUMBER	VARCHAR(15)
SET NOCOUNT ON;

CREATE TABLE #NUMEROS
(
NUMERO VARCHAR(15)
)
--SELECT @NUMBER = REPLACE(REPLACE(@PARAMETRO,CHAR(10),'+'),CHAR(13),'-')

WHILE LEN(@PARAMETRO) > 1
     BEGIN
			SET @INDEX = CHARINDEX (CHAR(10), @PARAMETRO) - 1		--@INDEX AGARRA LA POSICION DEL PRIMER RETORNO DE CARRO
			PRINT @INDEX
				IF @INDEX > 0
                BEGIN
					SET @NUMBER = SUBSTRING(@PARAMETRO, 1, @INDEX)
					SET @PARAMETRO = SUBSTRING(@PARAMETRO, @INDEX + 2, LEN(@PARAMETRO) - 1)
					INSERT INTO #NUMEROS (NUMERO)
					VALUES (@NUMBER)
				END
				IF @INDEX = -1
				BEGIN
					SET @NUMBER = SUBSTRING(@PARAMETRO, 1, LEN(@PARAMETRO))
					SET @PARAMETRO = ''
					INSERT INTO #NUMEROS (NUMERO)
                    VALUES (@NUMBER)
				END
	  END

--SELECT * FROM #NUMEROS

SELECT *
FROM TDRGT..ANBL_BLACK_LIST
WHERE IMSI_A 
IN
(SELECT NUMERO FROM #NUMEROS)

DROP TABLE #NUMEROS
END


--EXEC SP_TRAFICO '310260200230619
--310260200230603
--310260200230613'


