use std::fs;

//----- AutokeyKey
struct AutokeyKey {
  input_key_code: String,
  key_wait_key_code: String,
  output_key_code: String
}
fn create_autokey_key (
  input_key_code: String,
  key_wait_key_code: String,
  output_key_code: String ) -> AutokeyKey {
  return AutokeyKey{
    input_key_code: input_key_code.to_string(),
    key_wait_key_code: key_wait_key_code.to_string(),
    output_key_code: output_key_code.to_string()
  };
}

//----- resolve_to_autokey_syntax
fn resolve_to_autokey_syntax (filename: &'static str, key_array: Vec<AutokeyKey>) -> String {
  let input_template = fs::read_to_string(filename).expect("Unable to read file");

  let mut ret : String = String::new();
  for key in key_array.iter() {
    let mut tmp = input_template.clone();

    //HACK!!
    if key.key_wait_key_code == "null" {
      tmp = tmp.replace("KeyWait  #key_wait_key_code#\n", "");
    }
    tmp = tmp.replace("#input_key_code#",key.input_key_code.as_str());
    tmp = tmp.replace("#key_wait_key_code#",key.key_wait_key_code.as_str());
    tmp = tmp.replace("#output_key_code#",key.output_key_code.as_str());

    ret += tmp.as_str();
  }

  return ret;
}

fn create_autokey_array_from_file(filename: &'static str) -> Vec<AutokeyKey> {
  let mut key_array : Vec<AutokeyKey> = Vec::new();

  let data = fs::read_to_string(filename).expect("Unable to read file");
  for line in data.lines() {
    let keys_str : Vec<&str> = line.split(",").collect();
    if keys_str.len() !=3 {
       println!("Wrong number of parameter for line : {}", line);
       continue;
    }
    let key = create_autokey_key(keys_str[0].to_string(), keys_str[1].to_string(), keys_str[2].to_string());
    key_array.push(key);
  }

  return key_array;
}

fn main() {
  let key_array = create_autokey_array_from_file("keys.txt");
  let value = resolve_to_autokey_syntax("template.txt", key_array);
  fs::write("foo.txt", value).expect("Unable to write file");
}


