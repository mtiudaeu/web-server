mod utils;

use wasm_bindgen::prelude::*;

// When the `wee_alloc` feature is enabled, use `wee_alloc` as the global
// allocator.
#[cfg(feature = "wee_alloc")]
#[global_allocator]
static ALLOC: wee_alloc::WeeAlloc = wee_alloc::WeeAlloc::INIT;

#[wasm_bindgen]
extern {
    fn alert(s: &str);
}

#[wasm_bindgen]
pub fn greet(input: &str, template: &str) -> String {
    let key_array = create_autokey_array_from_file(input.to_string());
    let value = resolve_to_autokey_syntax(template.to_string(), key_array);

    return value;
}

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
fn resolve_to_autokey_syntax (input_template: String, key_array: Vec<AutokeyKey>) -> String {

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

fn create_autokey_array_from_file(data: String) -> Vec<AutokeyKey> {
  let mut key_array : Vec<AutokeyKey> = Vec::new();

  for line in data.lines() {
    let keys_str : Vec<&str> = line.split(",").collect();
    if keys_str.len() ==1 {
      key_array.push(
        create_autokey_key(keys_str[0].to_string(), keys_str[0].to_string(), keys_str[0].to_string()));
    }
    else if keys_str.len() ==3 {
      key_array.push(
        create_autokey_key(keys_str[0].to_string(), keys_str[1].to_string(), keys_str[2].to_string()));
    }
    else {
       println!("Wrong number of parameter for line : {}", line);
    }
  }

  return key_array;
}

