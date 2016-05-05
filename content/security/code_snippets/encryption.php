function uuid_gen($length = 32)
{
    $fp = @fopen('/dev/urandom', 'rb');
    $result = '';

    if ($fp !== FALSE) { //*\label{lst:fp_check}
        $result .= @fread($fp, $length);
        @fclose($fp);
    } else {
        trigger_error('Can not open /dev/urandom.');
    }

    return $result;
}