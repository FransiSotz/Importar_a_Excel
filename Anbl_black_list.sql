USE [PROGRAMAS]
GO

/****** Object:  Table [dbo].[ANBL_BLACK_LIST]    Script Date: 29/02/2024 09:24:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ANBL_BLACK_LIST](
	[tel_a] [varchar](31) NOT NULL,
	[imsi_a] [varchar](20) NOT NULL,
	[pais] [varchar](3) NOT NULL,
	[fecha_insercion] [datetime] NOT NULL,
	[reportado] [int] NOT NULL,
	[fecha_carga] [datetime] NULL,
	[tipo_carga] [int] NULL,
	[estado] [int] NULL,
 CONSTRAINT [PK_Anbl_Black_List] PRIMARY KEY CLUSTERED 
(
	[tel_a] ASC,
	[imsi_a] ASC,
	[pais] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 100, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ANBL_BLACK_LIST] ADD  CONSTRAINT [DF_Anbl_Black_List_reportado]  DEFAULT ((0)) FOR [reportado]
GO


