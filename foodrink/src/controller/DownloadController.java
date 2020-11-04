package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import arquivo.Arquivo;
import contexto.Contexto;

@Controller
@RequestMapping(value = { "/publico", "/confirmacao" })
public class DownloadController {

	/* TRANSFERE O APLICATIVO ANDROID*/
	@RequestMapping(value = { "/baixar-app" }, method = RequestMethod.GET)
	private void downloadApp(HttpServletResponse httpServletResponse) throws IOException {
		File file = new File(Contexto.getContexto("resources/img/app-foodrink-android.apk"));
		httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
		bufferedOutputStream.write(Arquivo.getByte(file.getAbsolutePath()));
		bufferedOutputStream.flush();
	}

}
