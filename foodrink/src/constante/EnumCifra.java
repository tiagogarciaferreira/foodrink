package constante;

public enum EnumCifra{

	ALGORITHM("RSA"),
	TAMANHO_CHAVE("1024"),
	CHAVE_PUBLICA("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClAWCf0U1rH/FTKZ74VxBSVMN/8Fiwqofl0WUp68yhCvR/nsOv6Q/2tl9zxs904Aqfn5Rr/Tqrywe6v9NoyXitcGf3vUVNOtUXk1pJnQ2A3XVXjmM6XWt9qBtXTu4z4yt2DZNVd7sHCOZ4CrRcqwZXwH0zsYaHoSvXsS2pZe7NdQIDAQAB"),
	CHAVE_PRIVADA("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKUBYJ/RTWsf8VMpnvhXEFJUw3/wWLCqh+XRZSnrzKEK9H+ew6/pD/a2X3PGz3TgCp+flGv9OqvLB7q/02jJeK1wZ/e9RU061ReTWkmdDYDddVeOYzpda32oG1dO7jPjK3YNk1V3uwcI5ngKtFyrBlfAfTOxhoehK9exLall7s11AgMBAAECgYA/7t2ybtWRaUoHD/xhetJ/JAqv2r9yjFClklGhQZUC3QzJGgn2cdZWoGkxodmmDlzvZlSK1w5RnBQ9nq/aTR3ta8N7DKT1ir2CWFASppzCPzACUVO8gpSLTQIN8ljDs6OtkLW/rKzg7EIbktyZeRHbw1/lgVruPj+JthwB3Ws/rQJBAPcROTTH/4ahK489x6DAfOGHMq9B8DZ3vZDxQRVygCuCyGEGCwikF/YQZp8De+bhk5dRdh02TzyDY/PnZz77GJ8CQQCq+J1xwWwnM8KAjaxUwxTmxD16Kb/r1/oao5HPUVGew4iUiNncA8sKdUmDuTjcoNdX9m5BY3Mlf9/Y1xRl3p1rAkEAsXDxQPMSlcJS4oFgShd0mRTcVfppZvUAdISQVbLyUk2PU/9fFUmk29hTm4iJB/yiX17mEXzb034Z3yYmhJkZyQJAUh/JwPKf+NSsAQQMW0q5p39r6FpVjfvYbyNSzrrI8fIKkQ6LV1ubFBLylQtLfO7lgaMlotMaZFDgPVBSw/wCsQJBAKvuyZhIX+AH7QxBjO5Eu858mo9szHPgKyI7i5MwCL6sVcNvwO3YWVlhnhw+/FudYiXdpEPszindqOlC2BP6Zm8=");
	
	private String value;

	EnumCifra(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}

