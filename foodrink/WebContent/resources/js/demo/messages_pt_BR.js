!function(e){"function"==typeof define&&define.amd?define(["jquery","../jquery.validate"],e):"object"==typeof module&&module.exports?module.exports=e(require("jquery")):e(jQuery)}(function(e){return e.extend(e.validator.messages,{required:"Campo obrigat\u00f3rio",remote:"Por favor, corrija este campo",email:"Email inv\u00e1lido",url:"Por favor, forne&ccedil;a uma URL v&aacute;lida.",date:"Por favor, forne&ccedil;a uma data v&aacute;lida.",dateISO:"Por favor, forne&ccedil;a uma data v&aacute;lida (ISO).",number:"Por favor, forne&ccedil;a um n&uacute;mero v&aacute;lido.",digits:"Por favor, forne&ccedil;a somente d&iacute;gitos",creditcard:"Por favor, forne&ccedil;a um cart&atilde;o de cr&eacute;dito v&aacute;lido.",equalTo:"Informe a mesma senha",maxlength:e.validator.format("Informe no m\u00e1ximo {0} caracteres"),minlength:e.validator.format("Informe no m\u00ednimo {0} caracteres"),rangelength:e.validator.format("Por favor, forne&ccedil;a um valor entre {0} e {1} caracteres de comprimento."),range:e.validator.format("Por favor, forne&ccedil;a um valor entre {0} e {1}."),max:e.validator.format("Por favor, forne&ccedil;a um valor menor ou igual a {0}."),min:e.validator.format("Por favor, forne&ccedil;a um valor maior ou igual a {0}."),step:e.validator.format("Por favor, forne&ccedil;a um valor m&acute;tiplo de {0}."),maxWords:e.validator.format("Por favor, forne&ccedil;a com {0} palavras ou menos."),minWords:e.validator.format("Por favor, forne&ccedil;a pelo menos {0} palavras."),rangeWords:e.validator.format("Por favor, forne&ccedil;a entre {0} e {1} palavras."),accept:"Tipo de imagem inv\u00e1lido",alphanumeric:"Por favor, forne&ccedil;a somente com letras, n&uacute;meros e sublinhados.",bankaccountNL:"Por favor, forne&ccedil;a com um n&uacute;mero de conta banc&aacute;ria v&aacute;lida.",bankorgiroaccountNL:"Por favor, forne&ccedil;a um banco v&aacute;lido ou n&uacute;mero de conta.",bic:"Por favor, forne&ccedil;a um c&oacute;digo BIC v&aacute;lido.",cifES:"Por favor, forne&ccedil;a um c&oacute;digo CIF v&aacute;lido.",creditcardtypes:"Por favor, forne&ccedil;a um n&uacute;mero de cart&atilde;o de cr&eacute;dito v&aacute;lido.",currency:"Por favor, forne&ccedil;a uma moeda v&aacute;lida.",dateFA:"Por favor, forne&ccedil;a uma data correta.",dateITA:"Por favor, forne&ccedil;a uma data correta.",dateNL:"Por favor, forne&ccedil;a uma data correta.",extension:"Formato de arquivo inv\u00e1lido",giroaccountNL:"Por favor, forne&ccedil;a um n&uacute;mero de conta corrente v&aacute;lido.",iban:"Por favor, forne&ccedil;a um c&oacute;digo IBAN v&aacute;lido.",integer:"Por favor, forne&ccedil;a um n&uacute;mero n&atilde;o decimal.",ipv4:"Por favor, forne&ccedil;a um IPv4 v&aacute;lido.",ipv6:"Por favor, forne&ccedil;a um IPv6 v&aacute;lido.",lettersonly:"Por favor, forne&ccedil;a apenas com letras.",letterswithbasicpunc:"Por favor, forne&ccedil;a apenas letras ou pontua&ccedil;ões.",mobileNL:"Por favor, fornece&ccedil;a um n&uacute;mero v&aacute;lido de telefone.",mobileUK:"Por favor, fornece&ccedil;a um n&uacute;mero v&aacute;lido de telefone.",nieES:"Por favor, forne&ccedil;a um NIE v&aacute;lido.",nifES:"Por favor, forne&ccedil;a um NIF v&aacute;lido.",nowhitespace:"Por favor, n&atilde;o utilize espa&ccedil;os em branco.",pattern:"O formato fornenecido &eacute; inv&aacute;lido.",phoneNL:"Por favor, fornece&ccedil;a um n&uacute;mero de telefone v&aacute;lido.",phoneUK:"Por favor, fornece&ccedil;a um n&uacute;mero de telefone v&aacute;lido.",phoneUS:"Por favor, fornece&ccedil;a um n&uacute;mero de telefone v&aacute;lido.",phonesUK:"Por favor, fornece&ccedil;a um n&uacute;mero de telefone v&aacute;lido.",postalCodeCA:"Por favor, fornece&ccedil;a um n&uacute;mero de c&oacute;digo postal v&aacute;lido.",postalcodeIT:"Por favor, fornece&ccedil;a um n&uacute;mero de c&oacute;digo postal v&aacute;lido.",postalcodeNL:"Por favor, fornece&ccedil;a um n&uacute;mero de c&oacute;digo postal v&aacute;lido.",postcodeUK:"Por favor, fornece&ccedil;a um n&uacute;mero de c&oacute;digo postal v&aacute;lido.",postalcodeBR:"Cep inv\u00e1lido.",require_from_group:e.validator.format("Por favor, forne&ccedil;a pelo menos {0} destes campos."),skip_or_fill_minimum:e.validator.format("Por favor, optar entre ignorar esses campos ou preencher pelo menos {0} deles."),stateUS:"Por favor, forne&ccedil;a um estado v&aacute;lido.",strippedminlength:e.validator.format("Por favor, forne&ccedil;a pelo menos {0} caracteres."),time:"Por favor, forne&ccedil;a um hor&aacute;rio v&aacute;lido, no intervado de 00:00 e 23:59.",time12h:"Por favor, forne&ccedil;a um hor&aacute;rio v&aacute;lido, no intervado de 01:00 e 12:59 am/pm.",url2:"Por favor, fornece&ccedil;a uma URL v&aacute;lida.",vinUS:"O n&uacute;mero de identifica&ccedil;&atilde;o de ve&iacute;culo informada (VIN) &eacute; inv&aacute;lido.",zipcodeUS:"Por favor, fornece&ccedil;a um c&oacute;digo postal americano v&aacute;lido.",ziprange:"O c&oacute;digo postal deve estar entre 902xx-xxxx e 905xx-xxxx",cpfBR:"Cpf inv\u00e1lido"}),e});