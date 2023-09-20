
import 'dart:io';

import 'package:path_provider/path_provider.dart';

class Utils {

  /// 2023.08.15 Added. 현재 폴더 및 현재 파일명, 현재 클래스명 얻기.
  Future<Directory> getCurrentDirectory() async {
    // final Directory currentDir1 = await getApplicationDocumentsDirectory(); /// 안 됨.
    final Directory currentDir2 = await getTemporaryDirectory(); /// 안 됨.
    // final Directory currentDir3 = await getDownloadsDirectory(); /// 안 됨.

    return currentDir2;

  }

}