import React from "react";

export const dialogContent = (msg: string): React.ReactNode => {
  return <div dangerouslySetInnerHTML={{__html: msg}}></div>
}
